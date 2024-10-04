package com.marvisx.jobms.job;

import com.marvisx.jobms.job.dto.JobWithCompanyDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {

    ResponseEntity<String> createJob(Job requestMap);

    ResponseEntity<List<JobWithCompanyDTO>> getAllJobs();
    ResponseEntity<JobWithCompanyDTO> getJob(Long id);
    ResponseEntity<String> updateJob( Job requestMap);
    ResponseEntity<String> deleteJob( Long id);
}
