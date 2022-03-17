package services;

import models.ProjectDetails;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FreeLancerServicesTest {

    FreeLancerServices freeLancerServices=new FreeLancerServices();

//    final Logger logger = LoggerFactory.getLogger("play");

    @Test
    public void testWordStatsIndevidual(){
        String answer = "{z=4, Apple=2, banana=1, x=1, y=1}";
        Map<String, Integer> map = freeLancerServices.wordStatsIndevidual("Apple banana x y z Apple z z z");
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
}