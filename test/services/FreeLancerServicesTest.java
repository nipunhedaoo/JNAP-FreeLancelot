package services;

import models.ProjectDetails;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class FreeLancerServicesTest {

    FreeLancerServices freeLancerServices=new FreeLancerServices();

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

