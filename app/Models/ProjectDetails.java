package Models;
import java.util.ArrayList;
import java.util.List;

public class ProjectDetails {

    private long ownerId;
    private List<Skills> skills;
    private long timeSubmitted;
    private String title;
    private String type;

    public ProjectDetails() {
        this.skills = new ArrayList<>();
        this.ownerId = -1;
        this.timeSubmitted = -1;
        this.title = null;
        this.type = null;
    }

    public class Skills{
        private String name;
    }
}


