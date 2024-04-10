package com.network.service;

import java.util.List;

import com.network.dto.ConnectionRequest;
import com.network.dto.UserDTO;
import com.network.dto.UserRequest;
import com.network.exception.LinkedInNetworkException;

public interface NetworkService {
    public boolean sendRequest(ConnectionRequest connectionRequest) throws LinkedInNetworkException;
       public boolean acceptRequest(ConnectionRequest connectionRequest);
       public boolean removeRequest(ConnectionRequest connectionRequest);
       public boolean removeConnection(ConnectionRequest connectionRequest);
       public List<String> getConnections(String userId) throws LinkedInNetworkException;
       public UserRequest getRequests(String userId) throws LinkedInNetworkException;
       List<UserDTO> getConnectionsDetails(String userId) throws LinkedInNetworkException;
       List<UserDTO> getRequestDetails(String userId) throws LinkedInNetworkException;
       List<UserDTO> notMyConnections(String userId) throws LinkedInNetworkException;
       boolean createConnection(String emailId) throws LinkedInNetworkException;
       public long getNextSequenceId(String key) throws LinkedInNetworkException;
}
