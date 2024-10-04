package com.marvisx.jobms.job.mapper;

import com.marvisx.jobms.external.Company.Company;
import com.marvisx.jobms.external.Company.Review;
import com.marvisx.jobms.job.Job;
import com.marvisx.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public class JobMapper {

    public static JobWithCompanyDTO jobWithCompanyDTO(Job job, Company company, List<Review> reviews){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReview(reviews);
        return jobWithCompanyDTO;
    }


}
