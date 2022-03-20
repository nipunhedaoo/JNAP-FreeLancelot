package services;

import controllers.HomeController;
import models.ProjectDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.test.WithApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    @Test
    public void testWordStatsIndevidual(){
        String answer = "{z=4, Apple=2, banana=1, x=1, y=1}";
        Map<String, Integer> map = freeLancerServices.wordStatsIndevidual("Apple banana x y z Apple z z z");
        assertEquals(map.toString(), answer);
    }

    @Test
    public void testWordStatsGlobal(){
        List <ProjectDetails> testProjectList = new ArrayList<>();
        testProjectList.add(new ProjectDetails(123, 234, null, 12345, "Test title 1", "Test type 1",null, "Apple banana x y z Apple z z z" ));
        testProjectList.add(new ProjectDetails(345, 234, null, 12345, "Test title 2", "Test type 2",null, "Apple banana x y z Apple z z z" ));

        String answer = "{z=8, Apple=4, banana=2, x=2, y=2}";

        Map<String, Integer> map = freeLancerServices.wordStatsGlobal(testProjectList);
        assertEquals(map.toString(), answer);
    }

    @Test
    public void testSearchSkills(){
        List<ProjectDetails> list= freeLancerServices.searchProjectsBySkill("9");
        for(ProjectDetails projectDetails : list)
        {
            assertTrue(checkSkillType(projectDetails.getSkills(),"JavaScript"));
        }
    }

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
