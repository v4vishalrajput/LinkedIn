package com.content.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.content.dto.CombinedDTO;
import com.content.dto.PostDTO;
import com.content.exception.UserException;
import com.content.service.ContentService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class ContentApi {

@Autowired
private ContentService service;
    
@GetMapping("/{emailId}")
public ResponseEntity<List<PostDTO>> getByEmailId(@PathVariable String emailId){
    List<PostDTO> postList = service.getByEmailId(emailId);
    
    return new ResponseEntity<>(postList, HttpStatus.ACCEPTED);
}

@GetMapping
public ResponseEntity<CombinedDTO> getAllPosts(){
    CombinedDTO postList = service.getAllPosts();
    
    return new ResponseEntity<>(postList, HttpStatus.ACCEPTED);
}


@PostMapping
public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO dto) throws UserException{
    PostDTO responseDTO = service.savePost(dto);
    return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
}
 @GetMapping("/engage/{postId}/{doneBy}")
 public ResponseEntity<Boolean> engage(@PathVariable Long postId, @PathVariable String doneBy) throws UserException{
        Boolean bool = service.likedDislike(postId, doneBy);
        return new ResponseEntity<>(bool, HttpStatus.ACCEPTED);
    }
 
 @PostMapping("/getMediaUrl")
 public ResponseEntity<String[]> saveMedia(@RequestPart("file") MultipartFile file ) throws IOException{
     String s[]= {service.saveMedia(file)};
     return new ResponseEntity<>(s, HttpStatus.CREATED);
 }
}
