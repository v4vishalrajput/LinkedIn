package com.recruitment.service;

import java.util.List;

import com.recruitment.dto.JobDTO;
import com.recruitment.exception.LinkedInException;

public interface JobService {
    public String saveJob(JobDTO dto) throws LinkedInException;

    public List<JobDTO> getAllJobs();

    List<String> saveJobs(List<JobDTO> dtoList) throws LinkedInException;

    List<JobDTO> getJobsLike(String title);

    JobDTO getJob(String jobId);

    List<JobDTO> getAppliedJobs(String email);


}
