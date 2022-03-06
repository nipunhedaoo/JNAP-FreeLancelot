package controllers;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Http;
import play.mvc.Result;
import javax.inject.Inject;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private final FormFactory formFactory;
	
	@Inject
	public HomeController(FormFactory formFactory) {
		this.formFactory = formFactory;
	}
	
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render(null));
//        return ok("Hello");
    }

    public Result search(Http.Request request) {
        DynamicForm form = formFactory.form().bindFromRequest(request);

        if(form.hasErrors()){
            return ok("Error in form");
        }else {
            String phrase = form.get("phrase");
            return ok(views.html.index.render(phrase));
        }
    }

}
