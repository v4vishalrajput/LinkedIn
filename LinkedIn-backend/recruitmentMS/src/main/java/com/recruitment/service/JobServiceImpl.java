package com.recruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recruitment.dto.JobDTO;
import com.recruitment.entity.AppliedEntity;
import com.recruitment.entity.JobEntity;
import com.recruitment.entity.SequenceId;
import com.recruitment.exception.LinkedInException;
import com.recruitment.repository.AppliedRepository;
import com.recruitment.repository.JobRepository;
import com.recruitment.utility.Transformations;

@Service
@Transactional
public class JobServiceImpl implements JobService {
  
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private AppliedRepository appliedRepository;
    
    @Autowired
    private MongoOperations mongoOperation;

    private static final String HOSTING_SEQ_KEY = "hosting";
    
    @Override
    public String saveJob(JobDTO dto) throws LinkedInException {
        
        JobEntity entity=Transformations.JobDtoToEntity(dto);
        entity.setId(this.getNextSequenceId(HOSTING_SEQ_KEY));
        
        return jobRepository.save(entity).getJobId();
    }

    @Override
    public List<JobDTO> getAllJobs() {
    
        List<JobEntity> entityList = jobRepository.findAll();
        return entityList.stream().map((e)-> Transformations.JobEntityToDto(e)).toList();
    }
    
    @Override
    public  List<String> saveJobs(List<JobDTO> dtoList)throws LinkedInException {
        List<JobEntity> entityList=new ArrayList();
        for(JobDTO dto : dtoList) {
            dto.setId(this.getNextSequenceId(HOSTING_SEQ_KEY));
             entityList.add(Transformations.JobDtoToEntity(dto));
             }
        
        return jobRepository.saveAll(entityList).stream().map(e->e.getJobId()).toList();
    }
    
    @Override
    public List<JobDTO> getJobsLike(String title){
       return jobRepository.findByJobTitleContainingIgnoreCase(title).stream().map((e)-> Transformations.JobEntityToDto(e)).toList();
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
    
    @Override
    public JobDTO getJob(String jobId) {
        return Transformations.JobEntityToDto(jobRepository.findByJobId(jobId));
    }
    
    @Override
    public List<JobDTO> getAppliedJobs(String email){
        
        List<JobDTO> jobList=getAllJobs();
        return jobList.stream().filter((job)->{
            Optional<AppliedEntity> opt = appliedRepository.findByJobIdAndEmail(job.getJobId(), email);
            return opt.isPresent();
        }).toList();
    }


}
