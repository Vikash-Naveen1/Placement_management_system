package com.placement.service;

import com.placement.controller.ApplicationController;
import com.placement.dto.ApplicationRequest;
import com.placement.dto.ApplicationResponse;
import com.placement.dto.StudentRequest;
import com.placement.entity.*;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.ApplicationRepository;
import com.placement.repository.JobRepository;
import com.placement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final StudentRepository studentRepository;

    public ApplicationService(ApplicationRepository applicationRepository,JobRepository jobRepository,StudentRepository studentRepository){
        this.applicationRepository=applicationRepository;
        this.jobRepository=jobRepository;
        this.studentRepository=studentRepository;
    }

    public ApplicationResponse createApplication(ApplicationRequest req){
        Student student=studentRepository.findById(req.getStudentId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Student not found with Id "+req.getStudentId()));

        Job job=jobRepository.findById(req.getJobId())
                .orElseThrow(()->
                        new ResourceNotFoundException(("Job not found with Id "+req.getJobId())));

        if(job.getStatus()!= JobStatus.OPEN){
            throw new IllegalStateException("Applications are not allowed for this job");
        }

        if(LocalDate.now().isAfter(job.getApplicationDeadline())){
            throw new IllegalStateException("Application deadline has passed");
        }

        if(applicationRepository.existsByStudentIdAndJobId(req.getStudentId(), req.getJobId())){
            throw new IllegalStateException("Student has already applied for this job");
        }

        Application app=new Application();
        app.setJob(job);
        app.setStudent(student);
        app.setStatus(ApplicationStatus.APPLIED);
        app.setAppliedAt(LocalDateTime.now());

        Application saved=applicationRepository.save(app);

        ApplicationResponse res=new ApplicationResponse();

        res.setId(saved.getId());
        res.setStudentId(saved.getStudent().getId());
        res.setJobId(saved.getJob().getId());
        res.setStatus(saved.getStatus());
        res.setAppliedAt(saved.getAppliedAt());

        return res;

    }
}
