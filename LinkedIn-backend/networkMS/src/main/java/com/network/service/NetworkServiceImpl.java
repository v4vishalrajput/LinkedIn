package com.network.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.network.dto.ConnectionRequest;
import com.network.dto.UserDTO;
import com.network.dto.UserRequest;
import com.network.entity.SequenceId;
import com.network.entity.User;
import com.network.exception.LinkedInNetworkException;
import com.network.repository.UserRepository;

@Service
@Transactional
public class NetworkServiceImpl implements NetworkService{
    private static final Log LOGGER = LogFactory.getLog(NetworkServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    
    private static final String HOSTING_SEQ_KEY = "hosting";
    
    @Autowired
    private MongoOperations mongoOperation;
    
    @Override
    public boolean createConnection(String emailId) throws LinkedInNetworkException {
           User user = new User(emailId);
           user.setId(this.getNextSequenceId(HOSTING_SEQ_KEY));
           LOGGER.info(user);
          userRepository.save(user);
          return true;
    }
    
    @Override
    public boolean sendRequest(ConnectionRequest connectionRequest) throws LinkedInNetworkException {
       Optional<User> fromUser = userRepository.findByEmailId(connectionRequest.getFrom());
      
            
           User userFrom = fromUser.get();
           List<String> sentConnectionRequests = userFrom.getSentConnectionRequests();
           sentConnectionRequests.add(connectionRequest.getTo());
          userFrom.setSentConnectionRequests(sentConnectionRequests);
           userRepository.save(userFrom);
         
       
         Optional<User> toUser = userRepository.findByEmailId(connectionRequest.getTo());
       
           User userTo = toUser.get();
           List<String> receivedConnectionRequests = userTo.getReceivedConnectionRequests();
           receivedConnectionRequests.add(connectionRequest.getFrom());
           userTo.setReceivedConnectionRequests(receivedConnectionRequests);
           userRepository.save(userTo);
           
        
         return true;
    }
    @Override
    public boolean acceptRequest(ConnectionRequest connectionRequest) {
        //Remove from sentConnectionRequests and add to connections
        LOGGER.info(connectionRequest);
        User fromUser = userRepository.findByEmailId(connectionRequest.getFrom()).get();
        List<String> connections = fromUser.getConnections();
        connections.add(connectionRequest.getTo());
        fromUser.setConnections(connections);
        List<String> sentConnectionRequests = fromUser.getSentConnectionRequests();
        sentConnectionRequests.remove(connectionRequest.getTo());
        fromUser.setSentConnectionRequests(sentConnectionRequests);
        userRepository.save(fromUser);
        //Remove from receivedConnectionRequests and add to connections
        User toUser = userRepository.findByEmailId(connectionRequest.getTo()).get() ;
        List<String> connections1 = toUser.getConnections();
        connections1.add(connectionRequest.getFrom());
        toUser.setConnections(connections1);
        List<String> receivedConnectionRequests = toUser.getReceivedConnectionRequests();
        receivedConnectionRequests.remove(connectionRequest.getFrom());
        toUser.setReceivedConnectionRequests(receivedConnectionRequests);
        userRepository.save(toUser);
        
        return true;
    }
    @Override
    public boolean removeRequest(ConnectionRequest connectionRequest) {
        //Remove from sentConnectionRequests
        User fromUser = userRepository.findByEmailId(connectionRequest.getFrom()).get();
        List<String> sentConnectionRequests = fromUser.getSentConnectionRequests();
        sentConnectionRequests.remove(connectionRequest.getTo());
        fromUser.setSentConnectionRequests(sentConnectionRequests);
        userRepository.save(fromUser);
        //Remove from receivedConnectionRequests
        User toUser = userRepository.findByEmailId(connectionRequest.getTo()).get() ;
        List<String> receivedConnectionRequests = toUser.getReceivedConnectionRequests();
        receivedConnectionRequests.remove(connectionRequest.getFrom());
        toUser.setReceivedConnectionRequests(receivedConnectionRequests);
        userRepository.save(toUser);
        
        return true;
    }
   @Override
    public boolean removeConnection(ConnectionRequest connectionRequest) {
        //Remove from connections
        User fromUser = userRepository.findByEmailId(connectionRequest.getFrom()).get();
        List<String> connections = fromUser.getConnections();
        connections.remove(connectionRequest.getTo());
        fromUser.setConnections(connections);
        userRepository.save(fromUser);
        //Remove from connections
        User toUser = userRepository.findByEmailId(connectionRequest.getTo()).get() ;
        List<String> connections1 = toUser.getConnections();
        connections1.remove(connectionRequest.getFrom());
        toUser.setConnections(connections1);
        userRepository.save(toUser);
        
        return true;
    }
    @Override
    public List<String> getConnections(String userId) throws LinkedInNetworkException{
        User user = userRepository.findByEmailId(userId).orElseThrow(() -> new LinkedInNetworkException("Service.USER_NOT_FOUND"));
        return user.getConnections();
    }
    //List of sent and received connection requests
    @Override
    public List<UserDTO> getConnectionsDetails(String userId) throws LinkedInNetworkException{
        User user = userRepository.findByEmailId(userId).orElseThrow(() -> new LinkedInNetworkException("Service.USER_NOT_FOUND"));
       return fetchDto(user.getConnections());
    }
    
  
    
    private List<UserDTO> fetchDto(List<String> userIds){
        
         WebClient client= WebClient.create();
         
         List<UserDTO> userdtos=new ArrayList();
         for(String u  : userIds) {
         UserDTO dto=client.get().uri("http://localhost:2000/user/"+u).retrieve().bodyToMono(UserDTO.class).block();
            userdtos.add(dto);
            LOGGER.info(dto);
         }
         
         return userdtos;
    }
    
    @Override
    public List<UserDTO> notMyConnections(String userId) throws LinkedInNetworkException{
        User user = userRepository.findByEmailId(userId).orElseThrow(() -> new LinkedInNetworkException("Service.USER_NOT_FOUND"));
        List<String> myConnections=user.getConnections();
        List<String> sentRequests=user.getSentConnectionRequests();
        List<String> receivedRequests=user.getReceivedConnectionRequests();
        
          WebClient client= WebClient.create();
          List<UserDTO> userdtos=client.get().uri("http://localhost:2000/user/getAllUsers").retrieve().bodyToFlux(UserDTO.class).collectList().block();
          
         return userdtos.stream().filter((dto)->{
            return !myConnections.contains(dto.getEmail()) && !sentRequests.contains(dto.getEmail()) && !receivedRequests.contains(dto.getEmail()) && !userId.equals(dto.getEmail());
         }).toList();
    }
    
    @Override
    public UserRequest getRequests(String userId) throws LinkedInNetworkException{
        User user = userRepository.findByEmailId(userId).orElseThrow(() -> new LinkedInNetworkException("Service.USER_NOT_FOUND"));
        UserRequest userRequest = new UserRequest();
        userRequest.setSentRequests(user.getSentConnectionRequests());
        userRequest.setReceivedRequests(user.getReceivedConnectionRequests());
        return userRequest;
    }
    
    @Override
    public List<UserDTO> getRequestDetails(String userId) throws LinkedInNetworkException {
          User user = userRepository.findByEmailId(userId).orElseThrow(() -> new LinkedInNetworkException("Service.USER_NOT_FOUND"));
          return fetchDto(user.getReceivedConnectionRequests());
    }
    
    
    @Override
    public long getNextSequenceId(String key) throws LinkedInNetworkException {
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        SequenceId seqId = mongoOperation.findAndModify(query, update, options, SequenceId.class);
        if (seqId == null) {
            throw new LinkedInNetworkException("Unable to get sequence id for key : " + key);
        }
        return seqId.getSeq();
    }
    
}