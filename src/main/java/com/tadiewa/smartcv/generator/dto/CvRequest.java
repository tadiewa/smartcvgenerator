/**
 * @author : tadiewa
 * date: 6/6/2025
 */


package com.tadiewa.smartcv.generator.dto;

public class CvRequest {
    private String name;
    private String skills;
    private String experience;
    private String targetJob;

    public CvRequest() {
    }

    public CvRequest(String name, String skills, String experience, String targetJob) {
        this.name = name;
        this.skills = skills;
        this.experience = experience;
        this.targetJob = targetJob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getTargetJob() {
        return targetJob;
    }

    public void setTargetJob(String targetJob) {
        this.targetJob = targetJob;
    }
}
