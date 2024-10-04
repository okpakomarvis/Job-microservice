package com.marvisx.jobms.client;

import com.marvisx.jobms.external.Company.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Company-Microservice")
public interface CompanyClient {
    @GetMapping("/companies/company/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
