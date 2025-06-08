/**
 * @author : tadiewa
 * date: 6/6/2025
 */


package com.tadiewa.smartcv.generator.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GeneratedContentStoreService {

    // This class will handle the storage of generated content
    // It will provide methods to save and retrieve generated CVs and cover letters
    // The implementation can use an in-memory store, a database, or any other storage solution

    // For simplicity, we can use a Map to store the content in memory
    // In a real application, we might want to use a database or a file system

    // private final Map<String, String> contentStore = new HashMap<>();

    // public void saveContent(String id, String content) {
    //     contentStore.put(id, content);
    // }

    // public String getContent(String id) {
    //     return contentStore.get(id);
    // }

    private final Map<String, String> contentMap = new ConcurrentHashMap<>();

    public String saveContent(String content) {
        String id = UUID.randomUUID().toString();
        contentMap.put(id, content);
        return id;
    }

    public String getContent(String id) {
        return contentMap.get(id);
    }
}
