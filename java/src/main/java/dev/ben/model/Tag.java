package dev.ben.model;

import org.springframework.stereotype.Component;

@Component
public class Tag {
    private int id;
    private int user_id;
    private String description;

    public Tag(int id, int user_id, String description) {
        this.id = id;
        this.user_id = user_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
