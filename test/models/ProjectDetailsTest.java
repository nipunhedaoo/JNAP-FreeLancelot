package models;

import org.junit.Test;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProjectDetailsTest {

  long projectId=64;
  long ownerId=123;
  List<List<String>> skills;
  long timeSubmitted=129823;
  String title="I need a programmer";
  String type="fixed";
   String preview_description="Abc";
   Map<String, Integer> wordStats;


  private ProjectDetails projectDetails=new ProjectDetails(projectId,ownerId,skills,timeSubmitted,title,type,wordStats,preview_description);
    @Test
    public void getOwnerId(){
        assertEquals(ownerId, projectDetails.getOwnerID());
    }
    @Test
    public void getSkills(){
        assertEquals(skills, projectDetails.getSkills());
    }
    @Test
    public void getTimeSubmitted(){
        assertEquals(new Date(Long.parseLong(timeSubmitted + "000")), projectDetails.getTimeSubmitted());
    }
    @Test
    public void getTitle(){
        assertEquals(title, projectDetails.getTitle());
    }
    @Test
    public void getType(){
        assertEquals(type, projectDetails.getType());
    }
    @Test
    public void getPreviewDesc(){
    assertEquals(preview_description, projectDetails.getPreviewDescription());
  }
    @Test
   public void getWordStats(){
    assertEquals(wordStats, projectDetails.getWordStats());
  }


}
