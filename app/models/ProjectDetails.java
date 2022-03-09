package models;

import java.util.*;

public class ProjectDetails {

    private long ownerId;
    private List<String> skills;
    private Date timeSubmitted;
    private String title;
    private String type;

    public ProjectDetails() {
        this.skills = new ArrayList<String>();
        this.ownerId = -1;
        this.timeSubmitted = new Date();
        this.title = null;
        this.type = null;
    }

    public ProjectDetails(long ownerId, List<String> skills, long timeSubmitted, String title, String type) {
        this.skills = skills;
        this.ownerId = ownerId;
        this.timeSubmitted = new Date(Long.parseLong(timeSubmitted + "000"));
        this.title = title;
        this.type = type;
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
}


