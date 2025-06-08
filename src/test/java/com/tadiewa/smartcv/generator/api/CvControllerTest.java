package com.tadiewa.smartcv.generator.api;

import com.tadiewa.smartcv.generator.dto.CvRequest;
import com.tadiewa.smartcv.generator.service.GeneratedContentStoreService;
import com.tadiewa.smartcv.generator.service.LLMService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CvControllerTest {

    @Mock
    private LLMService llmService;

    @Mock
    private GeneratedContentStoreService contentStore;

    private CvController cvController;

    @BeforeEach
    void setUp() {
        cvController = new CvController(llmService, contentStore);
    }

    @Test
    void generateCvShouldReturnValidResponse() {

        CvRequest request = new CvRequest(
            "John Doe",
            "Java, Spring Boot",
            "5 years of development",
            "Senior Developer"
        );

        when(llmService.callLLM(anyString())).thenReturn("Generated CV content");

        // Act
        ResponseEntity<Map<String, Object>> response = cvController.generateCv(request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("content"));
    }

    @Test
    void generateCvShouldHandleEmptyRequest() {
        CvRequest request = new CvRequest("", "", "", "");
        when(llmService.callLLM(anyString())).thenReturn("");
        ResponseEntity<Map<String, Object>> response = cvController.generateCv(request);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }
}
