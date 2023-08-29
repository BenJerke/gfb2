package com.techelevator.model;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Session {
    private int id;
    private int userId;
    private String notes;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;

    public Session (){

    };

    public Session(int id, int userId, String notes, LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
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

    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(LocalDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public LocalDateTime getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(LocalDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Duration getSessionDuration(){
        return Duration.between(this.endTimestamp, this.startTimestamp);
    }

}
