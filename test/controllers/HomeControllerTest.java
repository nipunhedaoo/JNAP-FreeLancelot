package controllers;

import models.ProjectDetails;
import models.SearchResultModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

import services.FreeLancerServices;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.*;

import play.cache.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class HomeControllerTest extends WithApplication {

    @Mock
    FreeLancerServices freeLancerServices;
    @Mock
    AsyncCacheApi cache;

    @Mock
    WSClient ws;

    @InjectMocks
    FreeLancerServices FreeLancerServicesMock;


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
    public void searchResultsTest() throws IOException, ExecutionException, InterruptedException {
        CompletionStage<WSResponse> repositoryDetails = FreeLancerServicesMock.searchResults("play");

        when(FreeLancerServicesMock.searchResults(any(String.class))).thenReturn(repositoryDetails);
        assertNotNull(repositoryDetails);

        LinkedHashMap<String, SearchResultModel> searchResults = new LinkedHashMap<>();
        List<ProjectDetails> res1 = new ArrayList<>();
        WSResponse res = repositoryDetails.toCompletableFuture().get();
        SearchResultModel repositoryDetails1 = null;

        try{
            FreeLancerServicesMock.searchProjectsBySkill(res);
            repositoryDetails1 = searchResults.put("xyz", new SearchResultModel(res1, 0.0, 0.0));
        }catch (Exception e){

        }

        assertEquals(repositoryDetails1.getprojectDetails(), new ProjectDetails());
    }

    private SearchResultModel repositoryDetailsObject(){
            SearchResultModel searchResult = new SearchResultModel();
            List<ProjectDetails> projectDets = new ArrayList<>();
            searchResult.setprojectDetails(projectDets);
            searchResult.setfleschKincaidGradeLevel(0.0);
            searchResult.setfleschReadabilityIndex(0.0);
            return searchResult;
    }

}
