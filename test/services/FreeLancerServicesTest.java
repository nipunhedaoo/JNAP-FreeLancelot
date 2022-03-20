package services;

import controllers.HomeController;
import helper.Session;
import models.ProjectDetails;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import play.Application;
import play.cache.AsyncCacheApi;
import play.data.FormFactory;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import play.test.WithApplication;

import javax.inject.Inject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.test.Helpers.running;

@RunWith(MockitoJUnitRunner.class)
public class FreeLancerServicesTest extends WithApplication {

    @Mock
    FreeLancerServices freeLancerServices;
    @Mock
    WSRequest wsRequest;
    @Mock
    WSClient wsClient;
    @InjectMocks
    HomeController homeController;




    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }
    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);
    }

//   @Test
//    public void testSearchSkills(){
//
//        List<ProjectDetails> list= ((List<ProjectDetails>) freeLancerServices.searchSkillResults("9").toCompletableFuture().thenApplyAsync(wsResponse -> {
//            try {
//                return freeLancerServices.searchProjectsBySkill(wsResponse);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }));
//        for(ProjectDetails projectDetails : list)
//        {
//            assertTrue(checkSkillType(projectDetails.getSkills(),"JavaScript"));
//        }
//    }


    public boolean checkSkillType(List<List<String>>skills,String skillname)
    {
        for(List<String>skill : skills)
        {
            if(skill.get(1).equalsIgnoreCase(skillname))
            {
                return true;
            }
        }
        return false;
    }
    @Test
    public void testUserTimeline() {

        running(provideApplication(), () -> {
            CompletionStage<WSResponse> obj = mock(CompletionStage.class);
            CompletableFuture completableFuture=mock(CompletableFuture.class);
            when(homeController.freelancerClient.searchSkillResults(any())).thenReturn(obj);
            when(obj.toCompletableFuture()).thenReturn(completableFuture);
            CompletionStage<Result> resultCompletionStage=homeController.searchBySkill("9","Java");
//            Result result = null;
//            try {
//                result = resultCompletionStage.toCompletableFuture().get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//            assertEquals(HttpStatus.SC_OK,result.status());
            });
    }
}