package com.placement.controller;

import com.placement.dto.ApplicationRequest;
import com.placement.dto.ApplicationResponse;
import com.placement.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService){
        this.applicationService=applicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApplicationResponse> createApplication(@Valid @RequestBody ApplicationRequest req){
        return new ResponseEntity<>(applicationService.createApplication(req), HttpStatus.CREATED);
    }

    @PutMapping("/shortlist/{id}")
    public ResponseEntity<ApplicationResponse> shortlist(@PathVariable Long id) {
        return new ResponseEntity<>(applicationService.shortList(id), HttpStatus.OK);
    }

    @PutMapping("/selected/{id}")
    public ResponseEntity<ApplicationResponse> selectedById(@PathVariable Long id){
        return new ResponseEntity<>(applicationService.selectApplication(id),HttpStatus.OK);
    }

    @PutMapping("/rejected/{id}")
    public ResponseEntity<ApplicationResponse> rejectedById(@PathVariable Long id){
        return new ResponseEntity<>(applicationService.rejectApplication(id),HttpStatus.OK);
    }
}
