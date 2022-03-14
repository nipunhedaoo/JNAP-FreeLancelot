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
    private String skillName;

    public ProjectDetails() {
        this.skills = new ArrayList<>();
        this.projectID = -1;
        this.ownerId = -1;
        this.timeSubmitted = new Date();
        this.title = null;
        this.type = null;
        this.preview_description = null;
        this.wordStats = null;
    }

    public ProjectDetails(long projectID, long ownerId, List<List<String>> skills, long timeSubmitted, String title, String type, Map<String, Integer> wordStats, String preview_description) {
        this.projectID = projectID;
        this.skills = skills;
        this.ownerId = ownerId;
        this.timeSubmitted = new Date(Long.parseLong(timeSubmitted + "000"));
        this.title = title;
        this.type = type;
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


