package controllers;

import models.ProjectDetails;
import models.SearchResultModel;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.ws.WSResponse;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

import services.FreeLancerServices;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import play.cache.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class HomeControllerTest extends WithApplication {

    @Mock
    FreeLancerServices freeLancerServices;
    @Mock
    AsyncCacheApi cache;

    @InjectMocks
    HomeController homeController;


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
    public void testSearchBySkill() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/projectBySkills/9/JavaScript");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void searchResultsTest()
    {
        running(provideApplication(), () -> {
            when(cache.getOrElseUpdate(any(),any())).thenReturn(repositoryDetailsObject());

            CompletionStage<WSResponse> repositoryDetails = freeLancerServices.searchResults("play");
            try {
                repositoryDetails.toCompletableFuture().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            assertTrue(repositoryDetails.toCompletableFuture().isDone());
        });
    }

    private CompletionStage<Object> repositoryDetailsObject(){
        return CompletableFuture.supplyAsync( () -> {
            SearchResultModel searchResult = new SearchResultModel();
            List<ProjectDetails> projectDets = new ArrayList<>();
            searchResult.setprojectDetails(projectDets);
            searchResult.setfleschKincaidGradeLevel(0.0);
            searchResult.setfleschReadabilityIndex(0.0);
            return searchResult;
        });
    }

}
