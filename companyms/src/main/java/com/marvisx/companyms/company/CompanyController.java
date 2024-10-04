package com.marvisx.companyms.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @GetMapping("/all")
    ResponseEntity<List<Company>> getAllJob(){
        try{
            return companyService.getAllCompany() ;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/save")
    ResponseEntity<String> createCompany(@RequestBody Company requestMap){
        try{
            if(requestMap !=null){
                //requestMap.setId(id++);
                return companyService.createCompany(requestMap);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/company/{id}")
    ResponseEntity<Company> getCompany(@PathVariable Long id){
        try{
            if(!Objects.isNull(id)) {
                return companyService.getCompany(id);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update")
    ResponseEntity<String> updateCompany(@RequestBody Company requestMap){
        try{
            return companyService.updateCompany(requestMap) ;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteCompany(@PathVariable Long id){
        try{
            return companyService.deleteCompany(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
