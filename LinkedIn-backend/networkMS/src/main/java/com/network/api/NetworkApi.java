package com.network.api;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.dto.ConnectionRequest;
import com.network.dto.UserDTO;
import com.network.dto.UserRequest;
import com.network.exception.LinkedInNetworkException;
import com.network.service.NetworkService;

@RestController
@RequestMapping("/network")
@CrossOrigin(origins = "http://localhost:4200")
public class NetworkApi {
    private static final Log LOGGER = LogFactory.getLog(NetworkApi.class);
       @Autowired
        private NetworkService networkService;
        @PostMapping("/sendRequest")
        public boolean addRequest(@RequestBody ConnectionRequest connectionRequest) throws LinkedInNetworkException {
            return networkService.sendRequest(connectionRequest);
        }
        @PostMapping("/acceptRequest")
        public boolean acceptRequest(@RequestBody ConnectionRequest connectionRequest) {
            return networkService.acceptRequest(connectionRequest);
        }
        @PostMapping("/rejectRequest")
        public boolean rejectRequest(@RequestBody ConnectionRequest connectionRequest) {
            return networkService.removeRequest(connectionRequest);
        }
        @PostMapping("/cancelRequest")
        public boolean cancelRequest(@RequestBody ConnectionRequest connectionRequest) {
            return networkService.removeRequest(connectionRequest);
        }
        @PostMapping("/removeConnection")
        public boolean removeConnection(@RequestBody ConnectionRequest connectionRequest) {
            return networkService.removeConnection(connectionRequest);
        }
        @GetMapping("/getConnections/{userId}")
        public List<String> getConnections(@PathVariable String userId) throws LinkedInNetworkException {
            return networkService.getConnections(userId);
        }
        @GetMapping("/getRequests/{userId}")
        public UserRequest getRequests(@PathVariable String userId) throws LinkedInNetworkException {
            return networkService.getRequests(userId);
        }
        
        @GetMapping("/getConnectionsDetails/{userId}")
        public List<UserDTO> getConnectionsDetails(@PathVariable String userId) throws LinkedInNetworkException {
            return networkService.getConnectionsDetails(userId);
        }
        
        
        @GetMapping("/getRequestsDetails/{userId}")
        public List<UserDTO> getRequestsDetails(@PathVariable String userId) throws LinkedInNetworkException {
            return networkService.getRequestDetails(userId);
        }
        
        @GetMapping("/getNonConnectionsDetails/{userId}")
        public List<UserDTO> getNonConnectionsDetails(@PathVariable String userId) throws LinkedInNetworkException {
            return networkService.notMyConnections(userId);
        }
        
        @GetMapping("/createConnection/{userId}")
        public boolean createConn(@PathVariable String userId) throws LinkedInNetworkException {
            return networkService.createConnection(userId);
        }
        
        
    }