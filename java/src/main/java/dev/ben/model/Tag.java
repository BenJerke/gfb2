package dev.ben.model;

import org.springframework.stereotype.Component;

@Component
public class Tag {
    private int id;
    private int userId;
    private String description;

    public Tag(int id, int userId, String description) {
        this.id = id;
        this.userId = userId;
        this.description = description;
    }
    public Tag(int userId, String description) {
        this.userId = userId;
        this.description = description;
    }

    public Tag(){

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
}
