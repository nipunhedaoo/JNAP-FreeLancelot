package controllers;

import play.libs.ws.WSClient;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Http;
import play.mvc.Result;
import services.FreeLancerServices;

import javax.inject.Inject;
import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private final FormFactory formFactory;

    FreeLancerServices freelancerClient;

    @Inject WSClient ws = null;

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
        if (this.freelancerClient.getWsClient() == null) {
            this.freelancerClient.setWsClient(ws);
        }
        if (searchKeyword == "") {
            return CompletableFuture.completedFuture(ok(views.html.index.render()));
        } else {
            CompletionStage<Map<String, String>> response = this.freelancerClient.searchResults(searchKeyword);
            return CompletableFuture.completedFuture(ok(views.html.index.render()));
        }
    }
}
