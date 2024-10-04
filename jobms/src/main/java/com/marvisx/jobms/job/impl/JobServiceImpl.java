package com.marvisx.jobms.job.impl;

import com.marvisx.jobms.client.CompanyClient;
import com.marvisx.jobms.client.ReviewClient;
import com.marvisx.jobms.external.Company.Company;
import com.marvisx.jobms.external.Company.Review;
import com.marvisx.jobms.job.Job;
import com.marvisx.jobms.job.JobRepository;
import com.marvisx.jobms.job.JobService;
import com.marvisx.jobms.job.dto.JobWithCompanyDTO;
import com.marvisx.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;

    @Override
    public ResponseEntity<String> createJob(Job requestMap) {
        try {
                jobRepository.save(requestMap);
                return new ResponseEntity<>("Job Created Successfully", HttpStatus.OK);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<JobWithCompanyDTO>> getAllJobs() {

        //List<JobWithCompanyDTO> jbWithCompany = new ArrayList<>();
        try{

            List<Job> jobs =jobRepository.findAll();

            if(!Objects.isNull(jobs)) {
                List<JobWithCompanyDTO> list = jobs.stream().map(this::convertDTO).collect(Collectors.toList());
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
private JobWithCompanyDTO convertDTO(Job job){
        //RestTemplate restTemplate = new RestTemplate();
        System.out.println("Company ID: " +job.getCompanyId());
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
    /*
        Company company = restTemplate.getForObject("http://COMPANYMS:8082/companies/company/" + job.getCompanyId(), Company.class);
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMS:8083/reviews?companyId="+ job.getCompanyId(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                });

        List<Review> reviews = reviewResponse.getBody();
*/
        //System.out.println("ID: " +company.getId());
        //Review review = restTemplate.getForObject("http://REVIEWMS:8083/reviews?companyId=" + company.getId(), Review.class);
        //System.out.println("Review: " +review.getId());
        JobWithCompanyDTO jobWithCompanyDTO = JobMapper.jobWithCompanyDTO(job, company, reviews);

        return jobWithCompanyDTO;
}
    @Override
    public ResponseEntity<JobWithCompanyDTO> getJob(Long id) {
        try {
            if(!Objects.isNull(id)){
                //Job job = jobs.stream().filter(e->e.getId().equals(id)).findFirst().get();
                Optional<Job> job = jobRepository.findById(id);

                if(job.isPresent()){
                    return new ResponseEntity<>(convertDTO(job.get()), HttpStatus.OK);
                }
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateJob(Job requestMap) {
        try{
            if(!Objects.isNull(requestMap)){
                //Job job = jobs.stream().filter(e->e.getId().equals(requestMap.getId())).findFirst().get();
                //int index = jobs.indexOf(job);
                Optional<Job> job = jobRepository.findById(requestMap.getId());
                if(job.isPresent()){
                    jobRepository.save(requestMap);
                    return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
                }
                return new ResponseEntity<>("Job Not Found ", HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteJob(Long id) {
        try {
            if(!Objects.isNull(id)){
                //Job job = jobs.stream().filter(e->e.getId().equals(id)).findFirst().get();
                Optional<Job> job  = jobRepository.findById(id);
                if(job.isPresent()){
                    jobRepository.deleteById(id);
                    return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
                }
                return new ResponseEntity<>("Job Id not found", HttpStatus.NOT_FOUND);
            }

        }catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
