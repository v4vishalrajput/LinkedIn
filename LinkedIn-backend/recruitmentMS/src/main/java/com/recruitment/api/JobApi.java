package com.recruitment.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment.dto.JobDTO;
import com.recruitment.dto.MultiJobDTO;
import com.recruitment.exception.LinkedInException;
import com.recruitment.service.AppliedServiceImpl;
import com.recruitment.service.JobService;

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "http://localhost:4200")
public class JobApi {

    @Autowired
    private JobService service;
    
    @Autowired
    private AppliedServiceImpl applyService;

    @GetMapping("/get")
    public ResponseEntity<List<JobDTO>> getJobs() {
        return new ResponseEntity<List<JobDTO>>(service.getAllJobs(), HttpStatus.ACCEPTED);
    }
    @PostMapping("/postJob")
    public ResponseEntity<String> saveJob(@RequestBody JobDTO dto) throws LinkedInException {
        return new ResponseEntity<>(service.saveJob(dto), HttpStatus.ACCEPTED);
    }

    @PostMapping("/postJob/multi")
    public ResponseEntity<List<String>> saveJob(@RequestBody MultiJobDTO multiJob) throws LinkedInException {
        
        return new ResponseEntity<>(service.saveJobs(multiJob.getJobListDTO()), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/get/{title}")
    public ResponseEntity<List<JobDTO>> getJobsLike(@PathVariable String title) {
        return new ResponseEntity<List<JobDTO>>(service.getJobsLike(title), HttpStatus.ACCEPTED);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable String id) {
        return new ResponseEntity<JobDTO>(service.getJob(id), HttpStatus.ACCEPTED);
    }
    
        @GetMapping("/toggle/{emailId}/{jobId}")
    public boolean toggle(@PathVariable String emailId, @PathVariable String jobId) throws LinkedInException {
        
        return applyService.toggle(jobId, emailId);
    }

    
        @GetMapping("/isApplied/{emailId}/{jobId}")
    public boolean isApplied(@PathVariable String emailId, @PathVariable String jobId) throws LinkedInException {
        
        return applyService.isApplied(jobId, emailId);
    }
    
        @GetMapping("/getAppliedJobs/{emailId}")
    public ResponseEntity<List<JobDTO>> getAppliedJobs(@PathVariable String emailId) {
        return new ResponseEntity<List<JobDTO>>(service.getAppliedJobs(emailId), HttpStatus.ACCEPTED);
    }

}