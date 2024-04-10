package com.recruitment.utility;

import com.recruitment.dto.JobDTO;
import com.recruitment.entity.JobEntity;

public class Transformations {
  public static JobEntity JobDtoToEntity(JobDTO dto) {
      JobEntity entity=new JobEntity();
      
      entity.setCompanyName(dto.getCompanyName());
      entity.setExperience(dto.getExperience());
      entity.setId(dto.getId());
      entity.setJobCategory(dto.getJobCategory());
      entity.setJobDescription(dto.getJobDescription());
      entity.setJobTitle(dto.getJobTitle());
      entity.setQualification(dto.getQualification());
      entity.setJobType(dto.getJobType());
      entity.setLocation(dto.getLocation());
      entity.setId(dto.getId());
      entity.setJobId(dto.getJobId());
      
      return entity;
  }
  
  public static JobDTO JobEntityToDto(JobEntity entity) {
      JobDTO dto=new JobDTO();
      
      dto.setCompanyName(entity.getCompanyName());
      dto.setExperience(entity.getExperience());
      dto.setId(entity.getId());
      dto.setJobCategory(entity.getJobCategory());
      dto.setJobDescription(entity.getJobDescription());
      dto.setJobTitle(entity.getJobTitle());
      dto.setQualification(entity.getQualification());
      dto.setJobType(entity.getJobType());
      dto.setLocation(entity.getLocation());
      dto.setId(entity.getId());
      dto.setJobId(entity.getJobId());
      
      return dto;
  }
}
