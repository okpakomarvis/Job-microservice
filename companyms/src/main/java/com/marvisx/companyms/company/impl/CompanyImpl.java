package com.marvisx.companyms.company.impl;

import com.marvisx.companyms.company.Company;
import com.marvisx.companyms.company.CompanyRepository;
import com.marvisx.companyms.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public ResponseEntity<List<Company>> getAllCompany() {
        try{

            return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> createCompany(Company requestMap) {
        try {
            Company company = new Company();
            //Job job = new Job();
            company.setDescription(requestMap.getDescription());
            company.setName(requestMap.getName());
            //company.setJobs(requestMap.getJobs());

            companyRepository.save(company);
            return new ResponseEntity<>("Company Created Successfully", HttpStatus.OK);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Company> getCompany(Long id) {
        try {
            if(!Objects.isNull(id)){
                //Job job = jobs.stream().filter(e->e.getId().equals(id)).findFirst().get();
                Optional<Company> company = companyRepository.findById(id);
                if(company.isPresent()){
                    return new ResponseEntity<>(company.get(), HttpStatus.OK);
                }
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCompany(Company requestMap) {
        try{
            if(!Objects.isNull(requestMap)){
                Optional<Company> company1 = companyRepository.findById(requestMap.getId());
                if(company1.isPresent()){
                    Company company = new Company();
                    company.setName(requestMap.getName());
                    company.setDescription(requestMap.getDescription());
                    //company.setJobs(requestMap.getJobs());
                    company.setId(requestMap.getId());
                    companyRepository.save(company);
                    return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
                }
                return new ResponseEntity<>("Company Not Found ", HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteCompany(Long id) {
        try {
            if(!Objects.isNull(id)){
                //Job job = jobs.stream().filter(e->e.getId().equals(id)).findFirst().get();
                Optional<Company> company  = companyRepository.findById(id);
                if(company.isPresent()){
                    companyRepository.deleteById(id);
                    return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
                }
                return new ResponseEntity<>("Company Id not found", HttpStatus.NOT_FOUND);
            }

        }catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
