package com.content.dto;

import java.time.LocalDateTime;
import java.util.List;
 
public class PostDTO {

    private Long id;

    private String emailId;

    private String content;

    private Integer likes;

    private String imageUrl;

    private LocalDateTime date;
    
    private List<String> peopleLiked;

    private boolean isLiked;
    
    public List<String> getPeopleLiked() {
        return peopleLiked;
    }

    public void setPeopleLiked(List<String> peopleLiked) {
        this.peopleLiked = peopleLiked;
    }


    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "PostDTO [id=" + id + ", emailId=" + emailId + ", content=" + content + ", likes=" + likes + ", date="
                + date + "]";
    }

}
