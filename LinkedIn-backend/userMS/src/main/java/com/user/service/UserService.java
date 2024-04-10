package com.user.service;

import java.util.List;

import com.user.dto.LoginDTO;
import com.user.dto.UserDTO;
import com.user.entity.User;
import com.user.exception.UserException;
import com.user.exception.UserNotFoundException;

public interface UserService{

    public UserDTO findByEmail(String email) throws UserException, UserNotFoundException;
    public UserDTO register(UserDTO dto) throws UserException;
    public UserDTO validateUser(LoginDTO dto) throws UserException, UserNotFoundException;
    public UserDTO resetPassword(LoginDTO dto) throws UserException, UserNotFoundException;
    public UserDTO profileCreate(String userId, UserDTO userDTO) throws UserException, UserNotFoundException;
    public UserDTO getUserProfile(String email) throws UserException, UserNotFoundException;
    public List<UserDTO> getAllUsers();
    boolean changeProfilePic(String imageUrl, String emailId) throws UserNotFoundException;
    public User findUserByEmailId(String email) throws UserNotFoundException;
    public long getNextSequenceId(String key) throws UserException;
}