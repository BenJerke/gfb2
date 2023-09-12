package dev.ben.model;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
//

@Component
public class Session {
    private int id;
    private int userId;
    private String notes;
    private long startTimestamp;
    private long endTimestamp;

    public Session (){

    };

    public Session(int id, int userId, String notes, long startTimestamp, long endTimestamp) {
        this.id = id;
        this.userId = userId;
        this.notes = notes;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public long getSessionDuration(){
        return this.endTimestamp - this.startTimestamp;
    }

}
