package com.tadiewa.smartcv.generator.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CvRequestTest {

    @Test
    void testCvRequestConstructorAndGetters() {
        String name = "John Doe";
        String skills = "Java, Spring Boot, REST APIs";
        String experience = "5 years software development";
        String targetJob = "Senior Java Developer";
        CvRequest request = new CvRequest(name, skills, experience, targetJob);
        assertEquals(name, request.getName());
        assertEquals(skills, request.getSkills());
        assertEquals(experience, request.getExperience());
        assertEquals(targetJob, request.getTargetJob());
    }

    @Test
    void testCvRequestSetters() {

        CvRequest request = new CvRequest();
        String name = "Jane Doe";
        String skills = "Python, Machine Learning";
        String experience = "3 years data science";
        String targetJob = "Data Scientist";


        request.setName(name);
        request.setSkills(skills);
        request.setExperience(experience);
        request.setTargetJob(targetJob);

        assertEquals(name, request.getName());
        assertEquals(skills, request.getSkills());
        assertEquals(experience, request.getExperience());
        assertEquals(targetJob, request.getTargetJob());
    }
}
