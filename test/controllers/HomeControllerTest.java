package controllers;

import models.ProjectDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import services.FreeLancerServices;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Mock
    FreeLancerServices freelancerClient;

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testSearchBySkill() {
      when(freelancerClient.searchProjectsBySkill("9")).thenReturn(new ArrayList<ProjectDetails>());
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/projectBySkills/9/JavaScript");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }


}
