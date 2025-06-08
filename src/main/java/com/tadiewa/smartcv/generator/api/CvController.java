/**
 * @author : tadiewa
 * date: 6/6/2025
 */


package com.tadiewa.smartcv.generator.api;

import com.tadiewa.smartcv.generator.dto.CvRequest;
import com.tadiewa.smartcv.generator.dto.CvResponse;
import com.tadiewa.smartcv.generator.service.GeneratedContentStoreService;
import com.tadiewa.smartcv.generator.service.LLMService;
import com.tadiewa.smartcv.generator.service.PdfGeneratorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cv")
public class CvController {
    // This class will handle the CV generation requests
    private final LLMService llmService;
    private final GeneratedContentStoreService contentStore;


    public CvController(LLMService llmService , GeneratedContentStoreService contentStore) {
        this.llmService = llmService;
        this.contentStore = contentStore;

    }


   @PostMapping("/generate")
    public ResponseEntity<Map<String, Object>> generateCv(@RequestBody CvRequest request) {
        String prompt = String.format(
                "Generate a professional CV and cover letter for: \nName: %s\nSkills: %s\nExperience: %s\nTarget Job: %s\n",
                request.getName(), request.getSkills(), request.getExperience(), request.getTargetJob()
        );

        String content = llmService.callLLM(prompt);
        if (content == null || content.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to generate CV content"));
        }
        String contentId = contentStore.saveContent(content);
        String pdfLink = "/api/cv/pdf/" + contentId;

        Map<String, Object> response = new HashMap<>();
        response.put("content", content);
        response.put("pdfDownloadLink", pdfLink);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String id) {
        String content = contentStore.getContent(id);
        if (content == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        byte[] pdfBytes = PdfGeneratorService.generatePdfFromText("Generated CV & Cover Letter", content);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "cv-cover-letter.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
