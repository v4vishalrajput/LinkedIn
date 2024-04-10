package com.user.entity;

public class Education {
    private String institution;
    private String degree;
    private String fieldOfStudy;
    
    
    
   public Education(String institution, String degree, String fieldOfStudy) {
       super();
       this.institution = institution;
       this.degree = degree;
       this.fieldOfStudy = fieldOfStudy;
   }
   public Education() {
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
       return "Education [institution=" + institution + ", degree=" + degree + ", fieldOfStudy=" + fieldOfStudy + "]";
   }
    
    
}
