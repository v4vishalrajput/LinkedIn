package com.network.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @Id
    private Long id;
    private String emailId;
    private List<String> connections;
    private List<String> sentConnectionRequests;
    private List<String> receivedConnectionRequests;
    
    
    
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
    public List<String> getConnections() {
        return connections;
    }
    public void setConnections(List<String> connections) {
        this.connections = connections;
    }
    public List<String> getSentConnectionRequests() {
        return sentConnectionRequests;
    }
    public void setSentConnectionRequests(List<String> sentConnectionRequests) {
        this.sentConnectionRequests = sentConnectionRequests;
    }
    public List<String> getReceivedConnectionRequests() {
        return receivedConnectionRequests;
    }
    public void setReceivedConnectionRequests(List<String> receivedConnectionRequests) {
        this.receivedConnectionRequests = receivedConnectionRequests;
    }
    public User(String emailId) {
        this.id=null;
        this.emailId=emailId;
        connections=new ArrayList<>();
        sentConnectionRequests=new ArrayList<>();
        receivedConnectionRequests=new ArrayList<>();
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", emailId=" + emailId + ", connections=" + connections + ", sentConnectionRequests="
                + sentConnectionRequests + ", receivedConnectionRequests=" + receivedConnectionRequests + "]";
    }
    
    
    
}