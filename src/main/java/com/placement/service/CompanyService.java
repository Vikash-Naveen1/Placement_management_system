package com.placement.service;

import com.placement.dto.CompanyRequest;
import com.placement.dto.CompanyResponse;
import com.placement.entity.Company;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public CompanyResponse getCompanyById(Long id){
        Optional<Company> company=companyRepository.findById(id);

        if(company.isPresent()){
            Company c=company.get();
            CompanyResponse res=new CompanyResponse();
            res.setId(c.getId());
            res.setEmail(c.getEmail());
            res.setDescription(c.getDescription());
            res.setWebsite(c.getWebsite());
            res.setLocation(c.getLocation());
            return res;
        }
        else{
            throw new ResourceNotFoundException("Company not found with Id "+id);
        }
    }

    public List<CompanyResponse> getAllCompany(){
        List<Company> company=companyRepository.findAll();
        List<CompanyResponse> response=new ArrayList<>();
        int i=0;
        while(i<company.size()){
            Company c=company.get(i);
            CompanyResponse res=new CompanyResponse();
            res.setId(c.getId());
            res.setName(c.getName());
            res.setEmail(c.getEmail());
            res.setWebsite(c.getWebsite());
            res.setLocation(c.getLocation());
            res.setDescription(c.getDescription());
            response.add(res);
            i++;
        }
        return response;
    }
}
