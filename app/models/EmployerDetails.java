package models;
import java.util.*;
public class EmployerDetails {
    private String username;
    private String email;
    private String primaryLanguage;
    private String profileDescription;
    private Date registrationDate;
    private Double hourlyRate;
    private List<ProjectDetails> employer_projects;
    private String firstName;
    private String lastName;


    public EmployerDetails(){
        this.username =null;
        this.email=null;
        this.primaryLanguage=null;
        this.profileDescription=null;
        this.registrationDate=new Date();
        this.hourlyRate=0.0;
        this.employer_projects = new ArrayList<>();
        this.firstName=null;
        this.lastName=null;

    }

    public EmployerDetails( String username,String email,String primaryLanguage,String profileDescription,long registrationDate,Double hourlyRate, List<ProjectDetails> employer_projects,String firstName,String lastName) {
        this.username = username;
        this.email=email;
        this.primaryLanguage=primaryLanguage;
        this.profileDescription=profileDescription;
        this.registrationDate=new Date(Long.parseLong( registrationDate+ "000"));
        this.hourlyRate=hourlyRate;
        this.employer_projects=employer_projects;
        this.firstName=firstName;
        this.lastName=lastName;
    }


    public String getUserName() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPrimaryLanguage(){
        return this.primaryLanguage;
    }

    public String getProfileDescription(){
        return this.profileDescription;
    }

    public Date getRegistrationDate(){
        return this.registrationDate;
    }

    public Double getHourlyRate(){
        return this.hourlyRate;
    }

    public String getFirstName(){return this.firstName;}

    public String getLastName(){return this.lastName;}

    public List<ProjectDetails> getProjects() {
        return this.employer_projects;
    }



}

