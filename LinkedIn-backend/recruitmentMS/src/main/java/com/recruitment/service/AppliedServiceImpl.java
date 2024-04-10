package com.recruitment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recruitment.entity.AppliedEntity;
import com.recruitment.entity.SequenceId;
import com.recruitment.exception.LinkedInException;
import com.recruitment.repository.AppliedRepository;

@Transactional
@Service
public class AppliedServiceImpl {
    @Autowired
    private AppliedRepository repo;
    
    @Autowired
    private MongoOperations mongoOperation;
    
    private static final String APPLY_SEQ_KEY = "apply";
    
    public boolean toggle(String jobId, String email) throws LinkedInException {
        Optional<AppliedEntity> opt=repo.findByJobIdAndEmail(jobId, email);
        if(opt.isEmpty()) {
            AppliedEntity entity=new AppliedEntity();
            entity.setEmail(email);
            entity.setJobId(jobId); 
            entity.setId(getNextSequenceId(APPLY_SEQ_KEY));
            
            repo.save(entity);
            return true;
            }else {
                repo.delete(opt.get());
                return false;
            }
    }
    public long getNextSequenceId(String key) throws LinkedInException {
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
    SequenceId seqId = mongoOperation.findAndModify(query, update, options, SequenceId.class);
        if (seqId == null) {
            throw new LinkedInException("Unable to get sequence id for key : " + key);
        }
        return seqId.getSeq();
    }
    public boolean isApplied(String jobId, String email) {
        Optional<AppliedEntity> opt=repo.findByJobIdAndEmail(jobId, email);
        boolean bool=false;
        if(opt.isPresent()) bool = true;
        System.out.println(bool);
        return bool;
}}
