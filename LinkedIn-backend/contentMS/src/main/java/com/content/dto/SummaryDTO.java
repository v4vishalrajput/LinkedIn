package com.content.dto;

public class SummaryDTO {
    private String skill;
    private String experience;
    private String aspiration;
    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getAspiration() {
        return aspiration;
    }
    public void setAspiration(String aspiration) {
        this.aspiration = aspiration;
    }
    @Override
    public String toString() {
        return "SummaryDTO [skill=" + skill + ", experience=" + experience + ", aspiration=" + aspiration + "]";
    }
    
    
}
