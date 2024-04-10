package com.network.dto;


public class UserDTO {
	
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


