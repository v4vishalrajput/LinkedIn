package com.network.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.network.entity.User;

public interface UserRepository extends MongoRepository<User,Long>{
    public Optional<User> findByEmailId(String emailId);
}
