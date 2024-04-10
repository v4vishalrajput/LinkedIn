package com.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
	@Null
    private Long id;
    
    @NotNull(message="{user.firstname.absent}")
    @Pattern(regexp="[A-Z][a-z]*", message="{user.name.invalid}")
    private String firstName;
    
    @NotNull(message="{user.lastname.absent}")
    @Pattern(regexp="[A-Z][a-z]*", message="{user.name.invalid}")
    private String lastName;
    

    @NotNull(message = "{user.emailid.absent}")
    @Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|org|in)$", message = "{user.emailid.invalid}")
    private String email;
    
    @NotNull(message="{user.password.absent}")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,16}$", message="{user.password.invalid}")
    private String password;

    @NotNull(message="{user.phone.absent}")
    @Pattern(regexp="[1-9][0-9]{9}", message="{user.phone.invalid}")
    private String phoneNo;
    
    private String image;
    private String headLine;
    private String jobTitle;
    private String location;
    private SummaryDTO summary;
    private EducationDTO education;

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



    public SummaryDTO getSummary() {
        return summary;
    }



    public void setSummary(SummaryDTO summary) {
        this.summary = summary;
    }



    public EducationDTO getEducation() {
        return education;
    }



    public void setEducation(EducationDTO education) {
        this.education = education;
    }



    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", phoneNo=" + phoneNo + ", image=" + image + ", headLine=" + headLine
                + ", jobTitle=" + jobTitle + ", location=" + location + ", summary=" + summary + ", education="
                + education + "]";
    }



//public static User dtoToEntity(UserDTO dto) {
//    User entity=new User();
//  
//    entity.setId(dto.getId());
//    entity.setEmail(dto.getEmail());
//    entity.setFirstName(dto.getFirstName());
//    entity.setLastName(dto.getLastName());
//    entity.setPassword(dto.getPassword());
//    entity.setPhoneNo(dto.getPhoneNo());
//    
//    return entity;
//  }
}


