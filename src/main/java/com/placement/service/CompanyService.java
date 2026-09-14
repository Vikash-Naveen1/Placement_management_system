package com.placement.service;

import com.placement.dto.CompanyRequest;
import com.placement.dto.CompanyResponse;
import com.placement.entity.Company;
import com.placement.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    CompanyService(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }

    public CompanyResponse createCompany(CompanyRequest companyRequest){
        Company c=new Company();
        c.setName(companyRequest.getName());
        c.setEmail(companyRequest.getEmail());
        c.setDescription(companyRequest.getDescription());
        c.setLocation(companyRequest.getLocation());
        c.setWebsite(companyRequest.getWebsite());

        Company saved=companyRepository.save(c);

        CompanyResponse res=new CompanyResponse();
        res.setId(saved.getId());
        res.setName(saved.getName());
        res.setEmail(saved.getEmail());
        res.setDescription(saved.getDescription());
        res.setLocation(saved.getLocation());
        res.setWebsite(saved.getWebsite());

        return res;
    }
}
