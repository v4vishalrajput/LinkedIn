package com.user.utility;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RestUtility {
    
    public void createConnection(String email) {
        WebClient client = WebClient.create();
        client.get().uri("http://localhost:2000/network/createConnection/"+email).retrieve().bodyToMono(Boolean.class).block();
        
    }

}

