package dev.benj.gfb2cliclient.services;

import dev.benj.gfb2cliclient.services.security.AuthService;
import org.springframework.web.client.RestTemplate;

public class SessionService {
    private RestTemplate restTemplate;
    private String token;
    public SessionService(AuthService authService, RestTemplate restTemplate){
        this.token = authService.getAccessToken();
        this.restTemplate = restTemplate;
    }


}
