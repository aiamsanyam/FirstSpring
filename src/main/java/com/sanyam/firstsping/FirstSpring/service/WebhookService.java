package com.sanyam.firstsping.FirstSpring.service;

import com.sanyam.firstsping.FirstSpring.model.SolutionRequest;
import com.sanyam.firstsping.FirstSpring.model.WebhookRequest;
import com.sanyam.firstsping.FirstSpring.model.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    @Autowired
    private RestTemplate restTemplate;
    
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String TEST_WEBHOOK_PREFIX = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";
    
    public WebhookResponse generateWebhook(WebhookRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);
        
        return restTemplate.postForObject(GENERATE_WEBHOOK_URL, entity, WebhookResponse.class);
    }
    
    public void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);
        
        SolutionRequest solutionRequest = new SolutionRequest(sqlQuery);
        HttpEntity<SolutionRequest> entity = new HttpEntity<>(solutionRequest, headers);
        
        restTemplate.postForObject(webhookUrl, entity, String.class);
    }
} 