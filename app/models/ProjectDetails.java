package models;

import java.util.*;

public class ProjectDetails {

    private long ownerId;
    private List<String> skills;
    private Date timeSubmitted;
    private String title;
    private String type;
    private String projectDescription;
    private double descriptionReadability;
    private String educationalLevel;

    public ProjectDetails() {
        this.skills = new ArrayList<String>();
        this.ownerId = -1;
        this.timeSubmitted = new Date();
        this.title = null;
        this.type = null;
        this.projectDescription = null;
        this.descriptionReadability = 0.0;
        this.educationalLevel = null;
    }

    public ProjectDetails(long ownerId, List<String> skills, long timeSubmitted, String title, String type, String projectDescription) {
        this.skills = skills;
        this.ownerId = ownerId;
        this.timeSubmitted = new Date(Long.parseLong(timeSubmitted + "000"));
        this.title = title;
        this.type = type;
        this.projectDescription= projectDescription;
    }

    public long getOwnerID() {
        return this.ownerId;
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

    public List<String> getSkills() {
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
}


