package com.user.utility;

import com.user.dto.EducationDTO;
import com.user.dto.SummaryDTO;
import com.user.dto.UserDTO;
import com.user.entity.Education;
import com.user.entity.Summary;
import com.user.entity.User;

public class Transformations {
    public static Education educationDtoToEntity(EducationDTO educationDto)
     {
         Education edu= new Education();
         edu.setDegree(educationDto.getDegree());
         edu.setFieldOfStudy(educationDto.getFieldOfStudy());
         edu.setInstitution(educationDto.getInstitution());
         return edu;
     }
     
    public static EducationDTO educationEntityToDto(Education education)
     {
         EducationDTO educationDto = new EducationDTO();
         educationDto.setDegree(education.getDegree());
         educationDto.setFieldOfStudy(education.getFieldOfStudy());
         educationDto.setInstitution(education.getInstitution());
         return educationDto ;
     }
     
    public static Summary summaryDtoToEntity(SummaryDTO summaryDto)
     {
         Summary sum = new Summary();
         sum.setSkill(summaryDto.getSkill());
         sum.setExperience(summaryDto.getExperience());
         sum.setAspiration(summaryDto.getAspiration());
         return sum;
     }
     
    public static SummaryDTO summaryEntityToDto(Summary summary)
     {
         SummaryDTO sumDto = new SummaryDTO();
         sumDto.setSkill(summary.getSkill());
         sumDto.setExperience(summary.getExperience());
         sumDto.setAspiration(summary.getAspiration());
         return sumDto;
     }
     
    public static User userDtoToEntity(UserDTO userDto)
     {
         User userEntity= new User();
         userEntity.setFirstName(userDto.getFirstName());
         userEntity.setLastName(userDto.getLastName());
         userEntity.setEmail(userDto.getEmail());
         userEntity.setHeadLine(userDto.getHeadLine());
         userEntity.setImage(userDto.getImage());
         userEntity.setJobTitle(userDto.getJobTitle());
         userEntity.setLocation(userDto.getLocation());
         userEntity.setPassword(userDto.getPassword());
         userEntity.setId(userDto.getId());
         userEntity.setPhoneNo(userDto.getPhoneNo());
         
         if(userDto.getEducation()!= null)
         {
             userEntity.setEducation(educationDtoToEntity(userDto.getEducation()));
         }
         if(userDto.getSummary()!=null)
         {
             userEntity.setSummary(summaryDtoToEntity(userDto.getSummary()));
         }
         return userEntity;
     }
     
    public static UserDTO userEntityToDto(User userEntity)
     {
         UserDTO userDto= new UserDTO();
         userDto.setFirstName(userEntity.getFirstName());
         userDto.setLastName(userEntity.getLastName());
         userDto.setEmail(userEntity.getEmail());
         userDto.setHeadLine(userEntity.getHeadLine());
         userDto.setImage(userEntity.getImage());
         userDto.setJobTitle(userEntity.getJobTitle());
         userDto.setLocation(userEntity.getLocation());
         userDto.setPassword(userEntity.getPassword());
         userDto.setId(userEntity.getId());
         userDto.setPhoneNo(userEntity.getPhoneNo());
         
         
         if(userEntity.getEducation()!= null)
         {
             userDto.setEducation(educationEntityToDto(userEntity.getEducation()));
         }
         if(userEntity.getSummary()!=null)
         {
             userDto.setSummary(summaryEntityToDto(userEntity.getSummary()));
         }
         return userDto;
     }
}