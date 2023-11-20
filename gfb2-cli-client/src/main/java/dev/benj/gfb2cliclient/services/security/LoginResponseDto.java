package dev.benj.gfb2cliclient.services.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.benj.gfb2cliclient.services.security.User;
import org.springframework.boot.jackson.JsonComponent;


public class LoginResponseDto {

    private String token;
    private User user;
    public LoginResponseDto(String token, User user) {
        this.token = token;
        this.user = user;
    }

//    @JsonProperty("token")
    String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }

//    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
