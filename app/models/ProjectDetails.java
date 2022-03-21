package models;

import java.util.*;

/**
 * This is the model class for project Details
 * @author Nipun Hedaoo
 * @author Alankrit Gupta
 * @author Jasleen Kaur
 * @author Pragya Tomar
 */
public class ProjectDetails {

    private long projectID;
    private long ownerId;
    private List<List<String>> skills;
    private Date timeSubmitted;
    private String title;
    private String type;
    private String preview_description;
    private Map<String, Integer> wordStats;
    private double  fleschReadabilityIndex;
    private double fleschKincaidGradeLevel;
    private String readability;

    public ProjectDetails() {
        this.skills = new ArrayList<>();
        this.projectID = -1;
        this.ownerId = -1;
        this.timeSubmitted = new Date();
        this.title = null;
        this.type = null;
        this.preview_description = null;
        this.wordStats = null;
        this.fleschReadabilityIndex = 0.0;
        this.fleschKincaidGradeLevel = 0.0;
        this.readability = "Early";
    }


    /**
     *
     * Parametrized Constructor with projectID, ownerId,skills,timeSubmitted,title,type,wordstats and preview_description
     * @param projectID projectID
     * @param ownerId ownerId
     * @param skills skills
     * @param timeSubmitted timeSubmitted
     * @param title title
     * @param type type
     * @param wordStats wordStats
     * @param preview_description preview_description
     * @param fleschKincaidGradeLevel  fleschKincaidGradeLevel
     * @param fleschReadabilityIndex  fleschReadabilityIndex
     * @param readability  readability
     */

    public ProjectDetails(long projectID, long ownerId, List<List<String>> skills, long timeSubmitted, String title, String type, Map<String, Integer> wordStats, String preview_description, Double fleschReadabilityIndex, Double fleschKincaidGradeLevel, String  readability) {
        this.projectID = projectID;
        this.skills = skills;
        this.ownerId = ownerId;
        this.timeSubmitted = new Date(Long.parseLong(timeSubmitted + "000"));
        this.title = title;
        this.type = type;
        this.wordStats = wordStats;
        this.preview_description = preview_description;
        this.fleschReadabilityIndex = fleschReadabilityIndex;
        this.fleschKincaidGradeLevel = fleschKincaidGradeLevel;
        this.readability = readability;
    }


    /**
     * This method gives the ownerID
     * @return long Returns the ownerID
     */
    public long getOwnerID() {
        return this.ownerId;
    }

    /**
     * This method generate the projectID
     * @return long Returns the Id of a project
     */
    public long getProjectID() {
        return this.projectID;
    }

    /**
     * This method is used to get the time of project submission
     * @return Date Returns the time Submitted
     */
    public Date getTimeSubmitted() {
        return this.timeSubmitted;
    }

    /**
     * This method is used to get the title of the project
     * @return String Returns the project title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * This method return the type of the project
     * @return String Returns the Project type
     */
    public String getType() {
        return this.type;
    }

    /**
     * This method is used to generate skills
     * @return List Returns the skills list
     */
    public List<List<String>> getSkills() {
        return this.skills;
    }

    /**
     * This method is used to get the word stats
     * @return Map Returns the word stats
     */
    public Map<String, Integer> getWordStats() {
        return this.wordStats;
    }

    /**
     * This method is used to get the preview description
     * @return String Returns the preview description
     */
    public String getPreviewDescription() {
        return this.preview_description;
    }

    /**
     * This method is used to get the Flesch Kincaid Grade Level
     * @return double Returns the value of Flesch Kincaid Grade Level
     */
    public double getFleschKincaidGradeLevel() {
        return this.fleschKincaidGradeLevel;
    }

    /**
     * This method is used to set the Kincaid Grade Level
     * @param fleschKincaidGradeLevel
     */
    public void setFleschKincaidGradeLevel(double fleschKincaidGradeLevel) {
        this.fleschKincaidGradeLevel = fleschKincaidGradeLevel;
    }

    /**
     * This method is used to get the Flesch Readability Index
     * @return double Returns the Flesch Readability Index
     */
    public double getFleschReadabilityIndex() {
        return this.fleschReadabilityIndex;
    }

    /**
     * This method is used to set the Flesch Readability Index
     * @param fleschReadabilityIndex
     */
    public void setFleschReadabilityIndex(double fleschReadabilityIndex) {
        this.fleschReadabilityIndex = fleschReadabilityIndex;
    }

    /**
     * This method is used to get the readability index
     * @return String Returns the readability index value
     */
    public String getReadability(){
        return this.readability;
    }

    /**
     * This method is used to set the readability index
     * @param fkcl
     */
    public void setReadability(double fkcl){
        String educationalLevel = "";
        if(fkcl > 100){
            educationalLevel = "Early" ;
        }else if(fkcl > 91){
            educationalLevel = "5th grade" ;
        }else if(fkcl > 81){
            educationalLevel = "6th grade" ;
        }else if(fkcl > 71 ){
            educationalLevel = "7th grade" ;
        }else if(fkcl > 66 ){
            educationalLevel = "8th grade" ;
        }else if(fkcl > 61 ){
            educationalLevel = "9th grade" ;
        }else if(fkcl > 51 ){
            educationalLevel = "High School" ;
        }else if(fkcl > 31 ){
            educationalLevel = "Some College" ;
        } else if(fkcl > 0 ){
            educationalLevel = "College Graduate" ;
        }else{
            educationalLevel = "Law School Graduate" ;
        }

        this.readability =educationalLevel;
    }

    public void setPreviewDescription(String previewDescription) {
         this.preview_description = previewDescription;
    }
}


