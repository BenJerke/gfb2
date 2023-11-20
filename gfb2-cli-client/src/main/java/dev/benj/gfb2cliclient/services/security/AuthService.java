package dev.benj.gfb2cliclient.services.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;

public class AuthService {
    private final String LOGIN_URL = "http://localhost:9000/login";
    private final String REGISTER_URL = "http://localhost:9000/register";
    RestTemplate restTemplate;
    ObjectMapper objectMapper;
    public AuthService(RestTemplate restTemplate, ObjectMapper objectMapper)  {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
    public String login(LoginDto loginDto) throws JsonProcessingException, AccessDeniedException {
        try{
            String dtoString = restTemplate.postForObject(LOGIN_URL, loginDto, String.class);
            JsonNode dtoNode = objectMapper.readTree(dtoString);
            if(dtoNode.get("token").isNull()){
                throw new AccessDeniedException("Access denied.");
            } else {
                return dtoNode.get("token").toString();
            }
        } catch (JsonProcessingException e){
            throw e;
        }
    }
    public void registerNewUser(RegDto regDto) throws JsonProcessingException{
        if(!regDto.getPassword().equals(regDto.getConfirmPassword())){
            System.out.println("Passwords don't match.");
        } else {
            String s = restTemplate.postForObject(REGISTER_URL, regDto, String.class);
            objectMapper.readTree(s).get("token").toString();
        }
    }


    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
