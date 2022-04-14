package controllers;

import actors.*;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.stream.Materializer;
import akka.util.Timeout;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.Session;
import models.EmployerDetails;
import models.ProjectDetails;
import models.SearchResultModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import play.cache.AsyncCacheApi;
import play.data.FormFactory;
import play.libs.streams.ActorFlow;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.WebSocket;
import scala.compat.java8.FutureConverters;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import services.FreeLancerServices;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static akka.pattern.Patterns.ask;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    WSClient wsClient;

    private final FormFactory formFactory;
    private AsyncCacheApi cache;
    private final Session session;

    final Logger logger = LoggerFactory.getLogger("play");

    public  FreeLancerServices freelancerClient;
    static LinkedHashMap<String, SearchResultModel> searchResults = new LinkedHashMap<String, SearchResultModel>();
    static LinkedHashMap<String, List<ProjectDetails>> skillSearchResults = new LinkedHashMap<String, List<ProjectDetails>>();

    List<ProjectDetails> listTest = new ArrayList<>(Arrays.asList(new ProjectDetails()));

    final ActorRef searchActor;
    final ActorRef wordstatsGlobalActor;
    final ActorRef fleschReadabilityActor;
    final ActorRef fleschKincadGradingActor;
    final ActorRef skillActor;
    final ActorRef employerActor;

    private final ActorSystem actorSystem;
    private final Materializer materializer;

    @Inject
    public HomeController(FormFactory formFactory, AsyncCacheApi cache, Session session, ActorSystem actorSystem, Materializer materializer) {
        this.formFactory = formFactory;

        this.freelancerClient = new FreeLancerServices(actorSystem, materializer);
        this.cache = cache;
        this.session = session;

        this.actorSystem = actorSystem;
        this.materializer = materializer;
        searchActor = actorSystem.actorOf(SearchActor.getProps());
        employerActor=actorSystem.actorOf(EmployerActor.getProps());
        wordstatsGlobalActor = actorSystem.actorOf(WordStatsGlobalActor.getProps());
        fleschReadabilityActor = actorSystem.actorOf(FleschReadingIndexActor.getProps());
        fleschKincadGradingActor = actorSystem.actorOf(FleschKincadGradingActor.getProps());
        skillActor=actorSystem.actorOf(SkillActor.getProps());
    }

//    @Inject
//    public HomeController(ActorSystem actorSystem, Materializer materializer) {
//        this.actorSystem = actorSystem;
//        this.materializer = materializer;
//        searchActor = actorSystem.actorOf(SearchActor.getProps());
//    }

    public WebSocket socket() {
        return WebSocket.Text.accept(request -> ActorFlow.actorRef(MyWebSocketActor::props, actorSystem, materializer));
    }
    /**
     * <p>An action that renders an HTML page with a welcome message.</p>
     * @param request It represents the WSResponse of API call made for the search keyword
     * @param searchKeyword It represents the WSResponse of API call made for the search keyword
     * @return It returns a list of Projects associated with the keyword.
     * @author Nipun Hedaoo
     * @author Alankrit Gupta
     */
    public CompletionStage<Result> index(Http.Request request, String searchKeyword) {
        CompletionStage<Result> resultCompletionStage = null;
        if (searchKeyword == "") {
            if (!session.isSessionExist(request)) {
                return CompletableFuture.completedFuture(ok(views.html.index.render(request, session.getSearchResultsHashMapFromSession(request, searchResults))).addingToSession(request, session.getSessionKey(), session.generateSessionValue()));
            } else {
                return CompletableFuture.completedFuture(ok(views.html.index.render(request, session.getSearchResultsHashMapFromSession(request, searchResults))));
            }
        } else {

            resultCompletionStage = FutureConverters.toJava(ask(searchActor, searchKeyword, 1000000))
                    .thenApply(response ->
                    {
                        try {

                            if(searchResults.containsKey(searchKeyword))
                            {
                                SearchResultModel temp=searchResults.get(searchKeyword);
                                searchResults.remove(searchKeyword);
                                searchResults.put(searchKeyword,temp);
                                logger.info("Search result is if it contains" +searchResults);
                            }
                            else {
                                List<ProjectDetails> array = new ArrayList<>();
                                array = freelancerClient.searchModelByKeyWord((JSONObject) response);

                                System.out.println("Array is " + array);
                                double fkcl = 0;
                                double fkgl = 0;

                                Timeout timeout = new Timeout(Duration.create(5, "seconds"));
                                Future<Object> futureFKCL = Patterns.ask(fleschReadabilityActor, array, 1000000);
                                fkcl = (Double) Await.result(futureFKCL, timeout.duration());

                                Future<Object> futureFKGL = Patterns.ask(fleschKincadGradingActor, array, 1000000);
                                fkgl = (Double) Await.result(futureFKGL, timeout.duration());

                                searchResults = FreeLancerServices.reverseFunc(searchResults);

                            searchResults.put(searchKeyword, new SearchResultModel(array, Math.round(fkcl), Math.round(Math.abs(fkgl))));
                        } }catch (Exception e) {
                            System.out.println("Exception in home controller "+e);
                        }

                        searchResults = FreeLancerServices.reverseFunc(searchResults);

                        session.setSessionSearchResultsHashMap(request, searchKeyword);

                        if (!session.isSessionExist(request)) {
                            return ok(views.html.index.render(request, session.getSearchResultsHashMapFromSession(request, searchResults))).addingToSession(request, session.getSessionKey(), session.generateSessionValue());
                        } else {
                            return ok(views.html.index.render(request, session.getSearchResultsHashMapFromSession(request, searchResults)));
                        }
                    }
                    );
            return resultCompletionStage;
        }
    }

    /**
     * This method is used to get the wordStats for a given query and Project Id.
     * @param query The query to get the word stats.
     * @param id The ProjectId used to get the word stats.
     * @param request Http request
     * @return Returns the word stats for a given query and an id.
     * @author Alankrit Gupta
     */
    public CompletionStage<Result> wordStats(String query,long id,Http.Request request) {
        List<ProjectDetails> results = searchResults.get(query) != null ? searchResults.get(query).getprojectDetails() : listTest;
        if (id != -1) {
            List<ProjectDetails> project = results
                    .stream()
                    .filter(item -> item.getProjectID() == id)
                    .collect(Collectors.toList());
            return CompletableFuture.completedFuture(ok(views.html.wordstats.render(request,project.get(0).getWordStats() , project.get(0).getPreviewDescription())));
        } else {
            return FutureConverters.toJava(ask(wordstatsGlobalActor, results, 1000000)).toCompletableFuture()
                    .thenApply(response ->
                            {
                                System.out.println("In func");
                                ObjectMapper oMapper = new ObjectMapper();
                                Map<String, Integer> map = oMapper.convertValue(response, Map.class);
                                System.out.println("Map is " +map);
                                return ok(views.html.wordstats.render(request,map, query));
                            }
                    );
        }
    }

    /**
     * <p>With this function all the lastest projects associated with skill can be fetched.</p>
     * @param skillId It represents the skillId associated with the skill
     * @param skillName It represents the name of the skill
     * @param request It respresent the Http request
     * @return It returns list of maximum 10 projects associated with the skill.
     * @author Jasleen Kaur
     */
    public CompletionStage<Result> searchBySkill(String skillId, String skillName,Http.Request request) {
        CompletionStage<Result> resultCompletionStage=null;
        if (!StringUtils.isEmpty(skillId) && !skillSearchResults.containsKey(skillId)) {
            if (freelancerClient.getWsClient() == null) {
                freelancerClient.setWsClient(wsClient);
            }

            resultCompletionStage =  FutureConverters.toJava(ask(skillActor, skillId, 1000000)).thenApply(res -> {
                        try {
                            logger.info("Cache");
                            List<ProjectDetails> respo= freelancerClient. searchProjectsBySkill((JSONObject)res);
                            skillSearchResults.put(skillId,respo);
                        } catch (JSONException e) {
                            logger.info("Error is parsing",e);
                        }
                        return (ok(views.html.skillSearch.render(request,skillSearchResults.get(skillId), skillName.replace("+", " "))));
                    }
            );
        }
        else{
            return  CompletableFuture.completedFuture(ok(views.html.skillSearch.render(request,skillSearchResults.get(skillId), skillName.replace("+", " "))));
        }
        return resultCompletionStage;
    }

    /**
     * This method is used to get the employer details for a given ownerId
     * @param ownerId This ownerId is used to get the details of an employer
     * @param request This represent the HTTP request
     * @return CompletionStage Returns the details of given ownerId
     * @author Pragya Tomar
     */
    public CompletionStage<Result> profilePage(String ownerId,Http.Request request) {
        CompletionStage<Result> resultCompletionStage = null;


        resultCompletionStage = FutureConverters.toJava(ask(employerActor, ownerId, 1000000))
                .thenApply(response ->
                        {
                            List<EmployerDetails> array = null;
                            try {

                                array = new ArrayList<>();
                                array = freelancerClient.employerResults(ownerId, (JSONObject) response);

                                System.out.println("Array is " + array);


                            } catch (Exception e) {
                                System.out.println("Exception in home controller " + e);
                            }
                            return ok(views.html.employerDetails.render(request,array, ownerId));
                        }
                );
        return resultCompletionStage;
    }

}