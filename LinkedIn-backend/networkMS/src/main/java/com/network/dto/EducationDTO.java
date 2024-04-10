package com.network.dto;

public class EducationDTO {
     private String institution;
     private String degree;
     private String fieldOfStudy;
     
     
     
    public EducationDTO(String institution, String degree, String fieldOfStudy) {
        super();
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
    }
    public EducationDTO() {
        // TODO Auto-generated constructor stub
    }
    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }
    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }
    @Override
    public String toString() {
        return "EducationDTO [institution=" + institution + ", degree=" + degree + ", fieldOfStudy=" + fieldOfStudy + "]";
    }
     
     
}
