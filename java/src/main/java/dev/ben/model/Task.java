package dev.ben.model;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Task {
    private int id;
    private int userId;
    private String description;
    private long estimatedDurationMilliseconds;
    private String notes;
    private int statusId;

    private long actualDurationMilliseconds;

    public long getEstimatedDurationMilliseconds() {
        return estimatedDurationMilliseconds;
    }

    public long getActualDurationMilliseconds() {
        return actualDurationMilliseconds;
    }

    public void setActualDurationMilliseconds(long actualDurationMilliseconds) {
        this.actualDurationMilliseconds = actualDurationMilliseconds;
    }

    private List<Tag> tags;

    public Task(int id, int userId, String description, long estimatedDuration, String notes, int statusId) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.estimatedDurationMilliseconds = estimatedDuration;
        this.notes = notes;
        this.statusId = statusId;
    }

    public Task(int id, int userId, String description,  String notes, int statusId) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.notes = notes;
        this.statusId = statusId;
    }

    public Task(int id, int userId, String description, int statusId) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.statusId = statusId;
    }

    public Task() {
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
    public void setEstimatedDurationMilliseconds(long estimatedDurationMilliseconds) {
        this.estimatedDurationMilliseconds = estimatedDurationMilliseconds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
