package com.content.utility;

import com.content.dto.PostDTO;
import com.content.entity.PostEntity;

public class Transformations {

    public static PostDTO PostEntitytoDto(PostEntity entity) {
        
        PostDTO dto=new PostDTO();
        
        dto.setContent(entity.getContent());
        dto.setId(entity.getId());
        dto.setLikes(entity.getLikes());
        dto.setEmailId(entity.getEmailId());
        dto.setDate(entity.getDate());
        dto.setImageUrl(entity.getImageUrl());
        dto.setPeopleLiked(entity.getPeopleLiked());
        
        return dto;
    }
    
    public static PostEntity PostDTOtoEntity(PostDTO dto) {
        
        PostEntity entity=new PostEntity();
        
        entity.setContent(dto.getContent());
        entity.setId(dto.getId());
        entity.setLikes(dto.getLikes());
        entity.setEmailId(dto.getEmailId());
        entity.setDate(dto.getDate());
        entity.setPeopleLiked(dto.getPeopleLiked());
        entity.setImageUrl(dto.getImageUrl());
        return entity;
    }
    
}
