package dev.ben.model;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Task {
    private int id;
    private int userId;
    private int projectId;
    private String description;
    private LocalDateTime creationTimestamp;
    private LocalDateTime initiationTimestamp;
    private LocalDateTime terminationTimestamp;
    private Duration estimatedDuration;
    private String notes;
    private int statusId;
    private List<Tag> tags;

    public Task(int id, int userId, String description, LocalDateTime creationTimestamp, LocalDateTime terminationTimestamp, Duration estimatedDuration, String notes, int statusId) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.creationTimestamp = creationTimestamp;
        this.terminationTimestamp = terminationTimestamp;
        this.estimatedDuration = estimatedDuration;
        this.notes = notes;
        this.statusId = statusId;
    }

    public Task(int id, int userId, String description, LocalDateTime creationTimestamp, Duration estimatedDuration, String notes, int statusId) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.creationTimestamp = creationTimestamp;
        this.estimatedDuration = estimatedDuration;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public LocalDateTime getTerminationTimestamp() {
        return terminationTimestamp;
    }

    public void setTerminationTimestamp(LocalDateTime terminationTimestamp) {
        this.terminationTimestamp = terminationTimestamp;
    }

    public Duration getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Duration estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
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

    public LocalDateTime getInitiationTimestamp() {
        return initiationTimestamp;
    }

    public void setInitiationTimestamp(LocalDateTime initiationTimestamp) {
        this.initiationTimestamp = initiationTimestamp;
    }
    public Duration getActualDuration(){
        return Duration.between(this.terminationTimestamp, this.initiationTimestamp);
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
