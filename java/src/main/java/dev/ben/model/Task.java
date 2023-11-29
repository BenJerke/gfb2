package dev.ben.model;

import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class Task {
    private int id;
    private int owningUserId;
    private List<Integer> allowedUserIds = new ArrayList<>();
    private String description;
    private Long estimatedDuration = 0L;
    private List<String> notes;
    private int statusId = 0;
    private Long actualDuration = 0L;
    private List<Tag> tags;

    /*
    - The only requirements for a task should be user ID and title. Everything else should be optional.

     */
    public Task(int id, int owningUserId, String description, Long estimatedDuration, Long actualDuration, List<Integer> allowedUserIds, List<Tag> tags, List<String> notes, int statusId) {
        this.id = id;
        this.owningUserId = owningUserId;
        this.allowedUserIds = allowedUserIds;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.actualDuration = actualDuration;
        this.notes = notes;
        this.tags = tags;
        this.statusId = statusId;
        addAllowedUser(owningUserId);
    }

    public Task(int owningUserId, String description, Long estimatedDuration, Long actualDuration, int statusId) {
        this.owningUserId = owningUserId;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.actualDuration = actualDuration;
        this.statusId = statusId;
        addAllowedUser(owningUserId);
    }

    public Task(int id, int owningUserId, String description, Long estimatedDuration, List<Tag> tags, List<String> notes, int statusId) {
        this.id = id;
        this.owningUserId = owningUserId;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.notes = notes;
        this.tags = tags;
        this.statusId = statusId;
        addAllowedUser(owningUserId);
    }

    public Task(int id, int owningUserId, String description, Long estimatedDuration, List<String> notes, int statusId) {
        this.id = id;
        this.owningUserId = owningUserId;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.notes = notes;
        this.statusId = statusId;
        addAllowedUser(owningUserId);
    }

    public Task(int id, int owningUserId, String description, List<String> notes, int statusId) {
        this.id = id;
        this.owningUserId = owningUserId;
        this.description = description;
        this.notes = notes;
        this.statusId = statusId;
        addAllowedUser(owningUserId);
    }

    public Task(int id, int owningUserId, String description, int statusId) {
        this.id = id;
        this.owningUserId = owningUserId;
        this.description = description;
        this.statusId = statusId;
        addAllowedUser(owningUserId);
    }
    public Task(int owningUserId, String description){
        this.owningUserId = owningUserId;
        this.description = description;
        addAllowedUser(owningUserId);
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwningUserId() {
        return owningUserId;
    }

    public void setOwningUserId(int owningUserId) {
        this.owningUserId = owningUserId;
        this.addAllowedUser(owningUserId);
    }
    public void setEstimatedDuration(Long estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
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

    public Long getEstimatedDuration() {
        return estimatedDuration;
    }

    public List<Integer> getAllowedUserIds() {
        return allowedUserIds;
    }

    public Long getActualDuration() {
        return actualDuration;
    }

    public void setAllowedUserIds(List<Integer> allowedUserIds) {
        this.allowedUserIds = allowedUserIds;
    }

    public void setActualDuration(Long actualDuration) {
        this.actualDuration = actualDuration;
    }

    public void addAllowedUser(int newUserId){
        if(this.allowedUserIds.contains(newUserId)){
            throw new ValidationException("Duplicate user.");
        } else {
            this.allowedUserIds.add(newUserId);
        }
    }
    public void removeAllowedUser(int userId){
        if(this.allowedUserIds.contains(userId)){
            this.allowedUserIds.remove(userId);
        } else {
            throw new ValidationException("User not present.");
        }
    }
    public void addTag(Tag tag){
        if(!this.tags.contains(tag)){
            this.tags.add(tag);
        } else {
            throw new ValidationException("Duplicate tag.");
        }

    }
    public void addNote(String note){
        this.notes.add(note);
    }

    public void deleteTag(Tag tag){
        if(this.tags.contains(tag)){
            this.tags.remove(tag);
        } else {
            throw new ValidationException("Tag not present.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && owningUserId == task.owningUserId && statusId == task.statusId && Objects.equals(allowedUserIds, task.allowedUserIds) && Objects.equals(description, task.description) && Objects.equals(estimatedDuration, task.estimatedDuration) && Objects.equals(notes, task.notes) && Objects.equals(actualDuration, task.actualDuration) && Objects.equals(tags, task.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owningUserId, allowedUserIds, description, estimatedDuration, notes, statusId, actualDuration, tags);
    }
}
