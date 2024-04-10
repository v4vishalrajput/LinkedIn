package com.user.api;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.LoginDTO;
import com.user.dto.UserDTO;
import com.user.exception.UserException;
import com.user.exception.UserNotFoundException;
import com.user.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@Validated
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserApi {
    private static final Log LOGGER = LogFactory.getLog(UserApi.class);
    @Autowired
    private UserService service;
    
 @GetMapping("/{email}")
 public ResponseEntity<UserDTO> getByEmail(@PathVariable @Email String email) throws UserException, UserNotFoundException {
     UserDTO user=service.findByEmail(email);
     return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
 }
 

 @PostMapping("/validate")
 public ResponseEntity<UserDTO> validateUser(@RequestBody @Valid LoginDTO dto) throws UserException, UserNotFoundException {
     UserDTO user=service.validateUser(dto);
     return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
 }
 
 @PostMapping("/register")
 public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO dto) throws UserException {
     UserDTO response= service.register(dto);
     return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
 }
 
 @PutMapping("/reset")
 public ResponseEntity<UserDTO> resetPassword(@RequestBody @Valid LoginDTO dto) throws UserException, UserNotFoundException {
     UserDTO response= service.resetPassword(dto);
     return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
 }
 
    @PutMapping("/profile/{userId}")
    public ResponseEntity<UserDTO> profileCreate(@PathVariable String userId, @RequestBody UserDTO userDTO)  throws UserException, UserNotFoundException {
        UserDTO userDto = service.profileCreate(userId,userDTO);
        LOGGER.info(userDto);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }
    
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDTO> getProfile(@PathVariable String userId /*@PathVariable String email*/)  throws UserException, UserNotFoundException{
        UserDTO userDTO = service.getUserProfile(userId);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
    
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> dtolist=service.getAllUsers();
        return new ResponseEntity<>(dtolist,HttpStatus.OK);
    }
 
 
 
 @PutMapping("/setProfileImage/{emailId}")
 public Boolean setProfile(@PathVariable String emailId,@RequestBody String imageUrl) throws UserNotFoundException {
     return  service.changeProfilePic(imageUrl, emailId);
 }
 
}
