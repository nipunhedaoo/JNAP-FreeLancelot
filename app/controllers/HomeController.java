package controllers;

import Models.ProjectDetails;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.FreeLancerServices;

import javax.inject.Inject;
import java.util.HashMap;
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
    static HashMap<String, List<ProjectDetails>> searchResults = new HashMap<>();

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
    public CompletionStage<Result> index(Http.Request request, String searchKeyword) {
        if (searchKeyword == "") {
            return CompletableFuture.completedFuture(ok(views.html.index.render()));
        } else {
            HashMap<String, List<ProjectDetails>> response = freelancerClient.searchResults(searchKeyword, searchResults);
            return CompletableFuture.completedFuture(ok(views.html.index.render(response)));
        }
    }
}
