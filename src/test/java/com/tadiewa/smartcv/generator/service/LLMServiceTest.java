package com.tadiewa.smartcv.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class LLMServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LLMService llmService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(llmService, "apiKey", "test-api-key");
        ReflectionTestUtils.setField(llmService, "model", "gpt-3.5-turbo");
        ReflectionTestUtils.setField(llmService, "maxTokens", 2000);
        ReflectionTestUtils.setField(llmService, "restTemplate", restTemplate);
    }

    @Test
    void testCallLLMSuccess() {
        String prompt = "Generate a CV";
        Map<String, Object> mockResponse = Map.of(
            "choices", java.util.List.of(
                Map.of("text", "Generated CV content")
            )
        );

        when(restTemplate.postForEntity(
            any(String.class),
            any(HttpEntity.class),
            eq(Map.class)
        )).thenReturn(ResponseEntity.ok(mockResponse));

        String result = llmService.callLLM(prompt);
        assertNotNull(result);
        assertTrue(result.contains("Generated CV content"));
    }

    @Test
    void testCallLLMWithEmptyPrompt() {

        String prompt = "";
        Map<String, Object> mockResponse = Map.of(
            "choices", java.util.List.of(
                Map.of("text", "")
            )
        );

        when(restTemplate.postForEntity(
            any(String.class),
            any(HttpEntity.class),
            eq(Map.class)
        )).thenReturn(ResponseEntity.ok(mockResponse));

        String result = llmService.callLLM(prompt);
        assertNotNull(result);
    }
}
