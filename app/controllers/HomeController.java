package controllers;

import helper.Session;
import models.ProjectDetails;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import play.data.FormFactory;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.FreeLancerServices;

import javax.inject.Inject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import play.cache.*;

import play.libs.ws.WSClient;

import static services.FreeLancerServices.wordStatsIndevidual;


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

    FreeLancerServices freelancerClient;
    static LinkedHashMap<String, List<ProjectDetails>> searchResults = new LinkedHashMap<>();
    static LinkedHashMap<String, List<ProjectDetails>> skillSearchResults = new LinkedHashMap<>();

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
     */
    public CompletionStage<Result> index(Http.Request request, String searchKeyword) {
        CompletionStage<Result> resultCompletionStage = null;
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
                    searchResults.put(searchKeyword, array);

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


    public Result wordStats(String query,long id) {
        List<ProjectDetails> results = searchResults.get(query);
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

    public CompletionStage<Result> searchBySkill(String skillId,String skillName) {
        if(!StringUtils.isEmpty(skillId) && !skillSearchResults.containsKey(skillId)) {
            List<ProjectDetails> list = freelancerClient.searchProjectsBySkill(skillId);
            skillSearchResults.put(skillId,list);
        }
        return CompletableFuture.completedFuture(ok(views.html.skillSearch.render(skillSearchResults.get(skillId), skillName)));
    }
}
