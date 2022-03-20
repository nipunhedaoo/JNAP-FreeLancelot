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
import play.libs.ws.WSResponse;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.test.Helpers.running;

@RunWith(MockitoJUnitRunner.class)
public class FreeLancerServicesTest extends WithApplication {

    @Mock
    FreeLancerServices freeLancerServices = new FreeLancerServices();
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


    @Test
    public void getNumOfWords(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";
        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();
        assertEquals(freeLancerServicesObj.getNumOfWords(projectDescription), 13);
        assertNotEquals(homeController.freelancerClient.getNumOfWords(projectDescription), 8);
    }

    @Test
    public void getNumOfSetences(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";
        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();
        assertEquals(freeLancerServicesObj.getNumOfSentences(projectDescription), 1);
        assertNotEquals(freeLancerServicesObj.getNumOfSentences(projectDescription), 3);
    }

    @Test
    public void getNnumOfSyllables(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";

        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();
        assertEquals(freeLancerServicesObj.getNnumOfSyllables(projectDescription), 25);
        assertNotEquals(freeLancerServicesObj.getNnumOfSyllables(projectDescription), 30);
    }


    @Test
    public void getfleschKancidGradeLevvel(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";

        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();

        int numOfSentence = 0;
        int numOfWords = 0;
        int numOfSyllables = 0;

        numOfSentence = freeLancerServicesObj.getNumOfSentences(projectDescription);
        numOfWords = freeLancerServicesObj.getNumOfWords(projectDescription);
        numOfSyllables = freeLancerServicesObj.getNnumOfSyllables(projectDescription);

        assertEquals(0, Double.compare(freeLancerServicesObj.calculateFKGL(numOfSentence, numOfWords, numOfSyllables), 1.2800000000000011));
        assertNotEquals(1, Double.compare(freeLancerServicesObj.calculateFKGL(numOfSentence, numOfWords, numOfSyllables), 13.3));
    }

    @Test
    public void getReadabilityIndex(){
        String projectDescription = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature.";
        FreeLancerServices freeLancerServicesObj = new FreeLancerServices();


        int numOfSentence = 0;
        int numOfWords = 0;
        int numOfSyllables = 0;

        numOfSentence = freeLancerServicesObj.getNumOfSentences(projectDescription);
        numOfWords = freeLancerServicesObj.getNumOfWords(projectDescription);
        numOfSyllables = freeLancerServicesObj.getNnumOfSyllables(projectDescription);

        assertEquals( 0, Double.compare(freeLancerServicesObj.calculateFRI(numOfSentence, numOfWords, numOfSyllables), 109.04000000000002));
        assertNotEquals(freeLancerServicesObj.calculateFRI(numOfSentence, numOfWords, numOfSyllables), 30);
    }



}
