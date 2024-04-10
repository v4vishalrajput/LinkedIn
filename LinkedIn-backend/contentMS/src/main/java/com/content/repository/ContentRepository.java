package com.content.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.content.entity.PostEntity;

public interface ContentRepository extends MongoRepository<PostEntity, Long> {

    public List<PostEntity> findAllByEmailId(String email);
    
}
