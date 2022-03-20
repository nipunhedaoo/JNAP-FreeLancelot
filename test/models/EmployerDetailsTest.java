package models;

import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployerDetailsTest
{
    String id="123";
    String username="abf";
    String registrationDate="1540";
    String limitedAccount="true";
    String displayName="abbf";
    String countryName="Canada";
    String role="Employer";
    String chosenRole="Employer";
    String emailVerified="true";
    String primaryCurrency="CAD";
    List<ProjectDetails>employer_projects;





private EmployerDetails employerDetails=new EmployerDetails(id,username,registrationDate,limitedAccount,displayName,countryName,role,chosenRole,emailVerified,primaryCurrency,employer_projects);
    @Test
    public void getUsername(){
        assertEquals(username,employerDetails.getUserName());
    }
    @Test
    public void getId(){
        assertEquals(id,employerDetails.getId());
    }
    @Test
    public void getLimitedAccount(){
        assertEquals(limitedAccount,employerDetails.getLimitedAccount());
    }
    @Test
    public void getDisplayName(){
        assertEquals(displayName,employerDetails.getDisplayName());
    }
    @Test
    public void getRegistrationDate(){
        assertEquals(registrationDate,employerDetails.getRegistrationDate());
    }
    @Test
    public void getCountryName(){
        assertEquals(countryName,employerDetails.getCountryName());
    }
    @Test
    public void getRole(){
        assertEquals(role,employerDetails.getRole());
    }
    @Test
    public void getChosenRole(){
        assertEquals(chosenRole,employerDetails.getChosenRole());
    }
    @Test
    public void getEmailVerified(){
        assertEquals(emailVerified,employerDetails.getEmailVerified());
    }
    @Test
    public void getPrimaryCurrency(){
        assertEquals(primaryCurrency,employerDetails.getPrimaryCurrency());
    }
    @Test
    public void getProjects(){
        assertEquals(employer_projects,employerDetails.getProjects());
    }

}
