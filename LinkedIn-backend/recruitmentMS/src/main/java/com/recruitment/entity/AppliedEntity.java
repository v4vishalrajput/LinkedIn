package com.recruitment.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applied")
public class AppliedEntity {

   @Id
    private Long id;
    private String email;
    private String jobId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    @Override
    public String toString() {
        return "AppliedEntity [id=" + id + ", email=" + email + ", jobId=" + jobId + "]";
    }
    
    
   
}
