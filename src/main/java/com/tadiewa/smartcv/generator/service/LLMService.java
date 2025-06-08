/**
 * @author : tadiewa
 * date: 6/6/2025
 */


package com.tadiewa.smartcv.generator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LLMService {

    // This class will handle the interaction with the LLM (Large Language Model)
    // It will send prompts to the LLM and receive responses
    // The implementation details will depend on the specific LLM being used (e.g., OpenAI, Hugging Face, etc.)


    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.model}")
    private String model;

    @Value("${spring.ai.openai.max-tokens}")
    private int maxTokens;

    @Value("${spring.ai.openai.url}")
    private String openaiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String callLLM(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "model", model,
                "prompt", prompt,
                "max_tokens", maxTokens
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(openaiUrl, entity, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                var choices = (java.util.List<Map<String, Object>>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    return (String) choices.get(0).get("text");
                }
            }
            return "No response from OpenAI.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

