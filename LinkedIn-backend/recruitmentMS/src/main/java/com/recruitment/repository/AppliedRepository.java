package com.recruitment.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.recruitment.entity.AppliedEntity;

public interface AppliedRepository extends MongoRepository<AppliedEntity, Long> {
	 public Optional<AppliedEntity> findByJobIdAndEmail(String jobId, String email);
	}