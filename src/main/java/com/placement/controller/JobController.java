package com.placement.controller;

import com.placement.dto.JobRequest;
import com.placement.dto.JobResponse;
import com.placement.repository.CompanyRepository;
import com.placement.repository.JobRepository;
import com.placement.service.CompanyService;
import com.placement.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/create")
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody JobRequest req){
        return new ResponseEntity<>(jobService.createJob(req), HttpStatus.CREATED);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id){
        return new ResponseEntity<>(jobService.getJobById(id),HttpStatus.OK);
    }

    @GetMapping("/job")
    public ResponseEntity<List<JobResponse>> getAllJobs(){
        return new ResponseEntity<>(jobService.getAll(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobResponse> updateById(@PathVariable Long id,@Valid @RequestBody JobRequest req){
        return new ResponseEntity<>(jobService.updateJobById(id,req),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<JobResponse> deleteById(@PathVariable Long id){
        jobService.deleteJob(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<JobResponse> approveJob(@PathVariable Long id) {
        return new ResponseEntity<>(jobService.approvalJob(id), HttpStatus.OK);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<JobResponse> rejectJob(@PathVariable Long id){
        return new ResponseEntity<>(jobService.rejectJob(id),HttpStatus.OK);
    }
}
