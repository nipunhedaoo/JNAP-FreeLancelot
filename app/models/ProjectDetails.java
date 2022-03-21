package models;

import java.util.*;

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

    public ProjectDetails(ArrayList<String> skills, int i, int i1, Date date, Object o, Object o1, Object o2, Object o3, double v, double v1, String early) {
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



    public long getOwnerID() {
        return this.ownerId;
    }

    public long getProjectID() {
        return this.projectID;
    }

    public Date getTimeSubmitted() {
        return this.timeSubmitted;
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }

    public List<List<String>> getSkills() {
        return this.skills;
    }

    public Map<String, Integer> getWordStats() {
        return this.wordStats;
    }

    public String getPreviewDescription() {
        return this.preview_description;
    }

    public double getFleschKincaidGradeLevel() {
        return this.fleschKincaidGradeLevel;
    }

    public void setFleschKincaidGradeLevel(double fleschKincaidGradeLevel) {
        this.fleschKincaidGradeLevel = fleschKincaidGradeLevel;
    }

    public double getFleschReadabilityIndex() {
        return this.fleschReadabilityIndex;
    }

    public void setFleschReadabilityIndex(double fleschReadabilityIndex) {
        this.fleschReadabilityIndex = fleschReadabilityIndex;
    }

    public String getReadability(){
        return this.readability;
    }

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


