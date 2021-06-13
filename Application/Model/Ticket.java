package Application.Model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Ticket extends BaseModel {
    private String title;
    private String description;
    private String submitter;
    private String team;
    private Instant creation;
    private String priority;

    public Ticket(String title, String description, String team, String priority) {
        this.title = title;
        this.description = description;
        this.team = team;
        this.priority = priority;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getSubmitter() {
        return submitter;
    }
    
    public void setSubmitter(String user) {
        this.submitter = user;
    }


    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    

    public Instant getCreation() {
        return creation;
    }

    public void setCreation(Instant date) {
        creation = date;
    }


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        String creationToString = creation.truncatedTo(ChronoUnit.SECONDS).toString().replaceAll("[TZ]", " ");
        return id + ". " + title + " | " + team + " | " + creationToString + "| " + priority;
    }

    public String printCreation() {
        return creation.truncatedTo(ChronoUnit.SECONDS).toString().replaceAll("[TZ]", " ");
    }
}