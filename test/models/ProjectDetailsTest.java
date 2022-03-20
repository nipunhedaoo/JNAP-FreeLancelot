package models;

import org.junit.Test;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProjectDetailsTest {

  private ProjectDetails projectDetailsDefualt = new ProjectDetails();

  long projectId=64;
  long ownerId=123;
  List<List<String>> skills;
  long timeSubmitted=129823;
  String title="I need a programmer";
  String type="fixed";
  String preview_description="Abc";
  String skillName = "skill";
  Map<String, Integer> wordStats;
  double fleschReadabilityIndex = 0;
  double fleschKincaidGradeLevel = 0;
  String readability = "readability";

  private ProjectDetails projectDetails=new ProjectDetails(projectId,ownerId,skills,timeSubmitted,title,type,wordStats,preview_description, fleschReadabilityIndex, fleschKincaidGradeLevel, readability);
    @Test
    public void getProjectId(){
        assertEquals(projectId, projectDetails.getProjectID());
    }
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
    public void getFleschReadabilityIndex(){
        assertEquals(0, Double.compare(fleschReadabilityIndex,projectDetails.getFleschReadabilityIndex()));
    }
    @Test
    public void getFleschKincaidGradeLevel(){
        assertEquals(0, Double.compare(fleschKincaidGradeLevel, projectDetails.getFleschKincaidGradeLevel()));
    }
    @Test
    public void getReadability(){
    assertEquals(readability, projectDetails.getReadability());
  }
    @Test
    public void setFleschKincaidGradeLevel(){
        projectDetails.setFleschKincaidGradeLevel(fleschKincaidGradeLevel);
    }
    @Test
    public void setReadability(){
      projectDetails.setReadability(101);
      projectDetails.setReadability(92);
      projectDetails.setReadability(82);
      projectDetails.setReadability(72);
      projectDetails.setReadability(67);
      projectDetails.setReadability(62);
      projectDetails.setReadability(52);
      projectDetails.setReadability(32);
      projectDetails.setReadability(1);
      projectDetails.setReadability(-1);
    }
    @Test
    public void getWordStats(){
        assertEquals(wordStats, projectDetails.getWordStats());
    }
}