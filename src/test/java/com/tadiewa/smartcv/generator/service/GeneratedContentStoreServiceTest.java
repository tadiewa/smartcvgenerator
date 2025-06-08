package com.tadiewa.smartcv.generator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GeneratedContentStoreServiceTest {

    private GeneratedContentStoreService contentStore;

    @BeforeEach
    void setUp() {
        contentStore = new GeneratedContentStoreService();
    }

    @Test
    void testSaveAndRetrieveContent() {

        String content = "Test CV content";


        String id = contentStore.saveContent(content);
        String retrievedContent = contentStore.getContent(id);

        assertNotNull(id);
        assertEquals(content, retrievedContent);
    }

    @Test
    void testRetrieveNonexistentContent() {

        String nonexistentId = "nonexistent-id";
        String retrievedContent = contentStore.getContent(nonexistentId);
        assertNull(retrievedContent);
    }

    @Test
    void testMultipleContentStorage() {
        String content1 = "First CV content";
        String content2 = "Second CV content";
        String id1 = contentStore.saveContent(content1);
        String id2 = contentStore.saveContent(content2);
        assertNotEquals(id1, id2);
        assertEquals(content1, contentStore.getContent(id1));
        assertEquals(content2, contentStore.getContent(id2));
    }
}
