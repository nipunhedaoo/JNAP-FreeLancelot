package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }


    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }


    @Test
    public void testIndex2() {

        Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/?searchKeyword=scala");
        Result result = route(app, request);
        assertEquals(OK, result.status());

    }

    @Test
    public void testSearchBySkill() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/projectBySkills/9/JavaScript");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testWordStats() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/react/wordStatsGlobal");

        Result result = route(app, request);
        assertEquals(OK,result.status());

        Http.RequestBuilder request2 = new Http.RequestBuilder()
                .method(GET)
                .uri("/react/wordStats/-2");

        Result result2 = route(app, request2);
        assertEquals(OK,result2.status());
    }


   @Test
    public void testProfilePage(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/profilePage/46621342");
        Result result = route(app,request);
        assertEquals(OK,result.status());
   }

}
