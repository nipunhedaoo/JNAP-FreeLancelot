package models;

import java.util.List;

/**
 * This is the model class for Employer Details
 * @author Pragya Tomar
 */
public class EmployerDetails {
    private String id;
    private String username;
    private String registrationDate;
    private String limitedAccount;
    private String displayName;
    private String countryName;
    private String role;
    private String chosenRole;
    private String emailVerified;
    private String primaryCurrency;
    private List<ProjectDetails> employer_projects;


    /**
     * @param id
     * @param username
     * @param registrationDate
     * @param limitedAccount
     * @param displayName
     * @param countryName
     * @param role
     * @param chosenRole
     * @param emailVerified
     * @param primaryCurrency
     * @param employer_projects
     */
    public EmployerDetails( String id,String username,String registrationDate,String limitedAccount,String displayName,String countryName,String role, String chosenRole,String emailVerified,String primaryCurrency, List<ProjectDetails> employer_projects) {
        this.id=id;
        this.username = username;
        this.registrationDate=registrationDate;
        this.limitedAccount=limitedAccount;
        this.displayName=displayName;
        this.countryName=countryName;
        this.role=role;
        this.chosenRole=chosenRole;
        this.emailVerified=emailVerified;
        this.primaryCurrency=primaryCurrency;
        this.employer_projects=employer_projects;

    }

    /**
     * This method gets the id
     * @return String returns the Id
     */
    public String getId(){return this.id;}

    /**
     * This method gets the UserName
     * @return String returns the UserName
     */
    public String getUserName() {
        return this.username;
    }

    /**
     * This method gets the Registration Date
     * @return String returns the Registration Date
     */
    public String getRegistrationDate(){
        return this.registrationDate;
    }

    /**
     * This method gets the Limited Account
     * @return String returns the Limited Account
     */
    public String getLimitedAccount(){return this.limitedAccount;}

    /**
     * This method gets the Display Name
     * @return String returns the Display Name
     */
    public String getDisplayName(){return this.displayName;}

    /**
     * This method gets the Country Name
     * @return String returns the Country Name
     */
    public String getCountryName(){return this.countryName;}

    /**
     * This method gets the Role
     * @return String returns the Role
     */
    public String getRole(){return this.role;}

    /**
     * @return String returns the Chosen Role
     */
    public String getChosenRole(){return this.chosenRole;}

    /**
     * This method gets the Email Verified
     * @return String returns the Email Verified
     */
    public String getEmailVerified(){return this.emailVerified;}

    /**
     * This method gets the Primary Currency
     * @return String returns the Primary Currency
     */
    public String getPrimaryCurrency(){return this.primaryCurrency;}

    /**
     * This method gets the Projects
     * @return List returns the Projects
     */
    public List<ProjectDetails> getProjects() {
        return this.employer_projects;
    }



}

