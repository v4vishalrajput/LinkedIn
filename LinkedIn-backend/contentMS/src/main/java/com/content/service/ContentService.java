package com.content.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.content.dto.CombinedDTO;
import com.content.dto.PostDTO;
import com.content.exception.UserException;

public interface ContentService {
public List<PostDTO> getByEmailId(String emailId);
public PostDTO savePost(PostDTO post) throws UserException;
public CombinedDTO getAllPosts();
Boolean likedDislike(Long id, String doneBy) throws UserException;
public String saveMedia(MultipartFile file) throws IOException;
}

