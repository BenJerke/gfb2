package dev.benj.gfb2cliclient.services.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.Deserializer;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;

public class AuthService {
    private final String logInUrl = "http://localhost:9000/login";
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    private String accessToken;
    public AuthService() {
    }


    public void login(LoginDto loginDto) throws AccessDeniedException{
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        ObjectMapper m = new ObjectMapper();

        HttpEntity<LoginDto> loginRequest = new HttpEntity<>(loginDto, headers);

        LoginResponseDto response = restTemplate.postForObject(logInUrl, loginRequest, LoginResponseDto.class);

        if(response != null){
            this.accessToken = response.getToken();
        } else {
            throw new AccessDeniedException("Access denied.");
        }
    }
    public void registerNewUser(LoginDto loginDto){

    }


    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
