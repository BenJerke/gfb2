package dev.benj.gfb2cliclient.services;

import dev.benj.gfb2cliclient.services.security.AuthService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {
    private RestTemplate restTemplate = new RestTemplate();
    private AuthService authService = new AuthService();
    public ApiClient() {
    }
}
