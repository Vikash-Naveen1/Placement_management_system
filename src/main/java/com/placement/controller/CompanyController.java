package com.placement.controller;

import com.placement.dto.CompanyRequest;
import com.placement.dto.CompanyResponse;
import com.placement.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    CompanyController(CompanyService companyService){
        this.companyService=companyService;
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyRequest req){
        return new ResponseEntity<>(companyService.createCompany(req), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id){
        return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CompanyResponse>> getAllCompany(){
        return new ResponseEntity<>(companyService.getAllCompany(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateById(@PathVariable Long id,@Valid @RequestBody CompanyRequest req){
        return new ResponseEntity<>(companyService.updateCompanyById(id,req),HttpStatus.OK);
    }

}
