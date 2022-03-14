package controllers;

import helper.Session;
import models.ProjectDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.FreeLancerServices;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final FormFactory formFactory;

    FreeLancerServices freelancerClient;
    static LinkedHashMap<String, List<ProjectDetails>> searchResults = new LinkedHashMap<>();

    private static int counter = 1;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
        this.freelancerClient = new FreeLancerServices();
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public CompletionStage<Result> index(Http.Request request, String searchKeyword, String skill) {
        System.out.println("job id" + skill);
        if (searchKeyword == "") {
            if (!Session.isSessionExist(request)) {
                counter+=1;
                return CompletableFuture.completedFuture(ok(views.html.index.render(Session.getSearchResultsHashMapFromSession(request, searchResults))).addingToSession(request,Session.getSessionKey(), Integer.toString(counter)));
            }
            else{
                return CompletableFuture.completedFuture(ok(views.html.index.render(Session.getSearchResultsHashMapFromSession(request, searchResults))));
            }

        } else {
            if(!searchResults.containsKey(searchKeyword)) {
                List<ProjectDetails> response = freelancerClient.searchResults(searchKeyword);
                searchResults.put(searchKeyword, response);
            }
            if(!StringUtils.isEmpty(skill))
            {
                freelancerClient.searchProjectsBySkill(skill);
            }
            Session.setSessionSearchResultsHashMap(request, searchKeyword);
            if (!Session.isSessionExist(request)) {
                return CompletableFuture.completedFuture(ok(views.html.index.render(Session.getSearchResultsHashMapFromSession(request, searchResults))).addingToSession(request,Session.getSessionKey(), Integer.toString(counter)));
            }
            else{
                return CompletableFuture.completedFuture(ok(views.html.index.render(Session.getSearchResultsHashMapFromSession(request, searchResults))));
            }

        }

    }

    public CompletionStage<Result> searchBySkill(String skillName) {
        System.out.println("Skill name is -- ");
        System.out.println(skillName);
        List<ProjectDetails> list=freelancerClient.searchProjectsBySkill(skillName);
        return CompletableFuture.completedFuture(ok(views.html.skillSearch.render(list,skillName)));
    }
}
