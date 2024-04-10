package com.user.dto;

public class LoginDTO {
 
    private String email;
    private String password;
    private String phoneNo;
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
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    @Override
    public String toString() {
        return "LoginDTO [email=" + email + ", password=" + password + ", phoneNo=" + phoneNo + "]";
    }
    
}
