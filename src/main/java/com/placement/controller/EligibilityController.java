package com.placement.controller;

import com.placement.dto.EligibilityRequest;
import com.placement.dto.EligibilityResponse;
import com.placement.service.EligibilityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService){
        this.eligibilityService=eligibilityService;
    }

    @PostMapping("/create")
    public ResponseEntity<EligibilityResponse> creatById(@Valid @RequestBody EligibilityRequest req){
        return new ResponseEntity<>(eligibilityService.createEligibility(req), HttpStatus.CREATED);
    }
}
