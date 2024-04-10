package com.recruitment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.recruitment.entity.JobEntity;

@Repository
public interface JobRepository extends MongoRepository<JobEntity, Long>{
    
    public List<JobEntity> findByJobTitleContainingIgnoreCase(String title);
    public JobEntity findByJobId(String id);
}


