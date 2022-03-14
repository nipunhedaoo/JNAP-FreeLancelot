package models;

import java.util.*;

public class ProjectDetails {

    private long projectID;
    private long ownerId;
    private List<List<String>> skills;
    private Date timeSubmitted; 
    private String title;
    private String type;
    private String projectDescription;
    private double descriptionReadability;
    private String educationalLevel;
    private String preview_description;
    private Map<String, Integer> wordStats;
    private String skillName;

    public ProjectDetails() {
        this.skills = new ArrayList<>();
        this.projectID = -1;
        this.ownerId = -1;
        this.timeSubmitted = new Date();
        this.title = null;
        this.type = null;
        this.projectDescription = null;
        this.descriptionReadability = 0.0;
        this.educationalLevel = null;
        this.preview_description = null;
        this.wordStats = null;
    }

    public ProjectDetails(long projectID, long ownerId, List<List<String>> skills, long timeSubmitted, String title, String type, String projectDescription, double descriptionReadability, String educationalLevel, Map<String, Integer> wordStats, String preview_description) {
        this.projectID = projectID;
        this.skills = skills;
        this.ownerId = ownerId;
        this.timeSubmitted = new Date(Long.parseLong(timeSubmitted + "000"));
        this.title = title;
        this.type = type;
        this.projectDescription = null;
        this.descriptionReadability = 0.0;
        this.educationalLevel = null;
        this.wordStats = wordStats;
        this.preview_description = preview_description;
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

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public double getDescriptionReadability() {
        return this.descriptionReadability;
    }

    public  void setDescriptionReadability(double descriptionReadability){ this.descriptionReadability = descriptionReadability;}

    public String getEducationalLevel() {
        return this.educationalLevel;
    }

    public  void setEducationalLevel(String educationalLevel){ this.educationalLevel = educationalLevel;}
    public Map<String, Integer> getWordStats() {
        return this.wordStats;
    }

    public String getPreviewDescription() {
        return this.preview_description;
    }

    public String getSkillName() {
        return this.skillName;
    }
}


