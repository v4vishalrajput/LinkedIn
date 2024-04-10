package com.content.dto;

import java.util.List;

public class CombinedDTO {

    List<PostDTO> postList;
    List<UserDTO> userList;
    

    public CombinedDTO(List<PostDTO> pList, List<UserDTO> uList){
        postList=pList;
        userList=uList;
    }


    public List<PostDTO> getPostList() {
        return postList;
    }


    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }


    public List<UserDTO> getUserList() {
        return userList;
    }


    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }


    @Override
    public String toString() {
        return "CombinedDTO [postList=" + postList + ", userList=" + userList + "]";
    }
    
    
}
