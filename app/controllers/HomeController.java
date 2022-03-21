package controllers;

import helper.Session;
import models.EmployerDetails;
import models.ProjectDetails;
import models.SearchResultModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import play.cache.AsyncCacheApi;
import play.data.FormFactory;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.FreeLancerServices;

import javax.inject.Inject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static services.FreeLancerServices.wordStatsIndevidual;


/**
 * This controller contains an action to handle HTTP requests for searching projects on the given skill,for fetching the
 * employer details,for displaying the word level statistics, and for determining the index readability.
 *
 * @author Jasleen Kaur
 * @author Pragya Tomar
 * @author Alankrit Gupta
 * @author Nipun Hedaoo
 */
public class HomeController extends Controller {

    @Inject
    WSClient wsClient;

    private final FormFactory formFactory;
    private AsyncCacheApi cache;
    private final Session session;

    final Logger logger = LoggerFactory.getLogger("play");

    FreeLancerServices freelancerClient;
    static LinkedHashMap<String, SearchResultModel> searchResults = new LinkedHashMap<>();
    static LinkedHashMap<String, List<ProjectDetails>> skillSearchResults = new LinkedHashMap<>();


    /**
     * Parametrized Constructor with formFactory, cache and session
     * @param formFactory Form Factory
     * @param cache AsyncCacheApi
     * @param session Session
     */
    @Inject
    public HomeController(FormFactory formFactory, AsyncCacheApi cache, Session session) {
        this.formFactory = formFactory;
        this.freelancerClient = new FreeLancerServices();
        this.cache=cache;
        this.session = session;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     *
     */
    public CompletionStage<Result> index(Http.Request request, String searchKeyword) {
        CompletionStage<Result> resultCompletionStage = null;
        DecimalFormat df = new DecimalFormat("#.##");

        if (searchKeyword == "") {
            if (!session.isSessionExist(request)) {
                return CompletableFuture.completedFuture(ok(views.html.index.render(session.getSearchResultsHashMapFromSession(request, searchResults))).addingToSession(request, session.getSessionKey(), session.generateSessionValue()));
            } else {
                return CompletableFuture.completedFuture(ok(views.html.index.render(session.getSearchResultsHashMapFromSession(request, searchResults))));
            }

        } else {
            if (freelancerClient.getWsClient() == null) {
                freelancerClient.setWsClient(wsClient);
            }

            List<ProjectDetails> array = new ArrayList<>();
            List<String> descriptionArray = new ArrayList<>();

            resultCompletionStage = cache.getOrElseUpdate((searchKeyword), () -> freelancerClient.searchResults(searchKeyword).toCompletableFuture().thenApplyAsync(res -> {
                try {

                    logger.info("cache");
                    JSONObject json = new JSONObject(res.getBody());
                    JSONObject result = json.getJSONObject("result");
                    JSONArray projects = (JSONArray) result.getJSONArray("projects");

                    for (int i = 0; i < projects.length(); i++) {
                        JSONObject object = projects.getJSONObject(i);

                        long projectID = Long.parseLong(object.get("id").toString());
                        long ownerId = Long.parseLong(object.get("owner_id").toString());
                        long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
                        String title = object.get("title").toString();
                        String type = object.get("type").toString();
                        String preview_description = object.get("preview_description").toString();
                        descriptionArray.add(preview_description);

                        Map<String, Integer> wordStats = wordStatsIndevidual(object.get("preview_description").toString());


                        JSONArray skills = object.getJSONArray("jobs");
                        List<List<String>> skillsList = new ArrayList<>();
                        for (int j = 0; j < skills.length(); j++) {
                            JSONObject skillObj = skills.getJSONObject(j);
                            List<String> skill = new ArrayList<>();
                            skill.add(skillObj.get("id").toString() + "/" + URLEncoder.encode(skillObj.get("name").toString(), String.valueOf(StandardCharsets.UTF_8)));
                            skill.add(skillObj.get("name").toString());
                            skillsList.add(skill);

                        }
                        array.add(new ProjectDetails(projectID, ownerId, skillsList, timeSubmitted, title, type, wordStats, preview_description));
                    }


                    double fkcl = freelancerClient.readabilityIndex(searchKeyword, array).orElse(0.0);
                    double fkgl = freelancerClient.fleschKancidGradeLevvel(searchKeyword, array).orElse(0.0);

                    searchResults.put(searchKeyword, new SearchResultModel(array, fkcl, fkgl));

                } catch (Exception e) {
                }

                session.setSessionSearchResultsHashMap(request, searchKeyword);
                if (!session.isSessionExist(request)) {
                    return ok(views.html.index.render(session.getSearchResultsHashMapFromSession(request, searchResults))).addingToSession(request, session.getSessionKey(), session.generateSessionValue());
                } else {
                    return ok(views.html.index.render(session.getSearchResultsHashMapFromSession(request, searchResults)));
                }
            }));
        }
        return resultCompletionStage;
    }


    /**
     * This method is used to get the wordStats for a given query and Project Id.
     * @param query The query to get the word stats.
     * @param id The ProjectId used to get the word stats.
     * @return Returns the word stats for a given query and an id.
     */
    public Result wordStats(String query,long id) {
        List<ProjectDetails> results = searchResults.get(query).getprojectDetails();
        if (id != -1) {
            List<ProjectDetails> project = results
                    .stream()
                    .filter(item -> item.getProjectID() == id)
                    .collect(Collectors.toList());
            return ok(views.html.wordstats.render(project.get(0).getWordStats(), project.get(0).getPreviewDescription()));
        } else {
            Map<String, Integer> wordMap = freelancerClient.wordStatsGlobal(results);
            return ok(views.html.wordstats.render(wordMap, query));
        }
    }

    /**
     * @param skillId This skillId is used to get the projects from a particular skillId
     * @param skillName The skillName for searching projects for a given skillName
     * @return CompletionStage<Result> Return the projects for th given skillId and skillName
     */
    public CompletionStage<Result> searchBySkill(String skillId,String skillName) {
        if(!StringUtils.isEmpty(skillId) && !skillSearchResults.containsKey(skillId)) {
            List<ProjectDetails> list = freelancerClient.searchProjectsBySkill(skillId);
            skillSearchResults.put(skillId,list);
        }
        return CompletableFuture.completedFuture(ok(views.html.skillSearch.render(skillSearchResults.get(skillId), skillName)));
    }

    /**
     * This method is used to get the employer details for a given ownerId
     * @param ownerId This ownerId is used to get the details and projects of an employer
     * @return CompletionStage<Result> Returns the employer details from given ownerId
     */
    public CompletionStage<Result> profilePage(String ownerId) {
        List<EmployerDetails> details=freelancerClient.employerResults(ownerId);
        return CompletableFuture.completedFuture(ok(views.html.employerDetails.render(details,ownerId)));
    }
}