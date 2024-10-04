package com.marvisx.companyms.company;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    ResponseEntity<List<Company>> getAllCompany();

    ResponseEntity<String> createCompany(Company requestMap);

    ResponseEntity<Company> getCompany(Long id);

    ResponseEntity<String> updateCompany(Company requestMap);

    ResponseEntity<String> deleteCompany(Long id);
}
