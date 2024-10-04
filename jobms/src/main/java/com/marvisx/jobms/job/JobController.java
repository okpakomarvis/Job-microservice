package com.marvisx.jobms.job;

import com.marvisx.jobms.job.dto.JobWithCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private Long id =1L;
    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    ResponseEntity<List<JobWithCompanyDTO>> getAllJob(){
        try{
            return jobService.getAllJobs() ;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
       return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/save")
    ResponseEntity<String> createJob(@RequestBody Job requestMap){
        try{
            if(requestMap !=null){
                //requestMap.setId(id++);
                return jobService.createJob(requestMap);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/job/{id}")
    ResponseEntity<JobWithCompanyDTO> getJob(@PathVariable Long id){
        try{
            if(!Objects.isNull(id)) {
                return jobService.getJob(id);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update")
    ResponseEntity<String> updateJob(@RequestBody Job requestMap){
        try{
            return jobService.updateJob(requestMap) ;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteJob(@PathVariable Long id){
        try{
            return jobService.deleteJob(id) ;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
