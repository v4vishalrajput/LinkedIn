package com.network.dto;

import java.util.List;

public class UserRequest {
private List<String> sentRequests;
private List<String> receivedRequests;
public List<String> getSentRequests() {
    return sentRequests;
}
public void setSentRequests(List<String> sentRequests) {
    this.sentRequests = sentRequests;
}
public List<String> getReceivedRequests() {
    return receivedRequests;
}
public void setReceivedRequests(List<String> receivedRequests) {
    this.receivedRequests = receivedRequests;
}
}
