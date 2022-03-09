package Models;

import java.util.Date;
import java.util.HashMap;

public class ProjectDetails {

    private long ownerId;
    private HashMap<String, Integer> skills;
    private Date timeSubmitted;
    private String title;
    private String type;

    public ProjectDetails() {
        this.skills = new HashMap<String, Integer>();
        this.ownerId = -1;
        this.timeSubmitted = new Date();
        this.title = null;
        this.type = null;
    }

    public ProjectDetails(long ownerId, HashMap<String, Integer> skills, long timeSubmitted, String title, String type) {
        this.skills = skills;
        this.ownerId = ownerId;
        this.timeSubmitted = new Date(Long.parseLong(timeSubmitted + "000"));
        this.title = title;
        this.type = type;
    }
}


