package com.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNo;
    
    private String image;
    private String headLine;
    private String jobTitle;
    private String location;
    private Summary summary;
    private Education education;

    public String getPhoneNo() {
        return phoneNo;
    }



    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }


    public Education getEducation() {
        return education;
    }



    public void setEducation(Education education) {
        this.education = education;
    }



    public String getImage() {
        return image;
    }



    public void setImage(String image) {
        this.image = image;
    }



    public String getHeadLine() {
        return headLine;
    }



    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }



    public String getJobTitle() {
        return jobTitle;
    }



    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }



    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {
        this.location = location;
    }



    public Summary getSummary() {
        return summary;
    }



    public void setSummary(Summary summary) {
        this.summary = summary;
    }



    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", phoneNo=" + phoneNo + ", image=" + image + ", headLine=" + headLine
                + ", jobTitle=" + jobTitle + ", location=" + location + ", summary=" + summary + ", education="
                + education + "]";
    }



//  public static UserDTO entityToDto(User entity) {
//      UserDTO dto = new UserDTO();
//      dto.setId(entity.getId());
//      dto.setEmail(entity.getEmail());
//      dto.setFirstName(entity.getFirstName());
//      dto.setLastName(entity.getLastName());
//      dto.setPassword(entity.getPassword());
//      dto.setPhoneNo(entity.getPhoneNo());
//      return dto;
//  }




}
