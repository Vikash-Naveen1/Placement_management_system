package com.placement.service;

import com.placement.dto.ApplicationRequest;
import com.placement.dto.ApplicationResponse;
import com.placement.entity.*;
import com.placement.exception.BusinessException;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.ApplicationRepository;
import com.placement.repository.JobRepository;
import com.placement.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j  //logging abstraction/API
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
        log.info("Creating application for student {} and job {}",req.getStudentId(),req.getJobId());
        Student student=studentRepository.findById(req.getStudentId())
                .orElseThrow(()->{
                        log.warn("Student {} not found",req.getStudentId());

                        return new ResourceNotFoundException("Student not found with Id "+req.getStudentId());
                });

        Job job=jobRepository.findById(req.getJobId())
                .orElseThrow(()->{
                    log.warn("Job {} not found",req.getJobId());

                    return new ResourceNotFoundException("Job not found with id "+req.getJobId());
                });

        if(job.getStatus()!= JobStatus.OPEN){
            log.warn("Application rejected because job {} is not OPEN. Status: {}",job.getId(),job.getStatus());
            throw new BusinessException("Applications are not allowed for this job");
        }

        if(LocalDate.now().isAfter(job.getApplicationDeadline())){
            throw new BusinessException("Application deadline has passed");
        }

        if(applicationRepository.existsByStudentIdAndJobId(req.getStudentId(), req.getJobId())){
            log.warn("Student {} already applied for job {}",req.getStudentId(),req.getJobId());
            throw new BusinessException("Student has already applied for this job");
        }

        Application app=new Application();
        app.setJob(job);
        app.setStudent(student);
        app.setStatus(ApplicationStatus.APPLIED);
        app.setAppliedAt(LocalDateTime.now());

        Application saved=applicationRepository.save(app);
        log.info("Application {} created successfully",saved.getId());

        ApplicationResponse res=new ApplicationResponse();

        res.setId(saved.getId());
        res.setStudentId(saved.getStudent().getId());
        res.setJobId(saved.getJob().getId());
        res.setStatus(saved.getStatus());
        res.setAppliedAt(saved.getAppliedAt());

        return res;

    }

    public ApplicationResponse shortList(Long id){
        Optional<Application> app=applicationRepository.findById(id);

        if(app.isPresent()){
            Application a=app.get();
            if(a.getStatus()!=ApplicationStatus.APPLIED){
                throw new BusinessException("Only applied candidates should be shortlisted");
            }
            a.setStatus(ApplicationStatus.SHORTLISTED);

            Application shortlisted=applicationRepository.save(a);

            ApplicationResponse res=new ApplicationResponse();

            res.setStudentId(shortlisted.getStudent().getId());
            res.setId(shortlisted.getId());
            res.setStatus(shortlisted.getStatus());
            res.setAppliedAt(shortlisted.getAppliedAt());
            res.setJobId(shortlisted.getJob().getId());

            return res;
        }
        else{
            throw new ResourceNotFoundException("Application not found with id "+id);
        }
    }

    public ApplicationResponse selectApplication(Long id){
        Optional<Application> app=applicationRepository.findById(id);

        if(app.isPresent()){
            Application a=app.get();
            if(a.getStatus()!=ApplicationStatus.INTERVIEW){
                throw new ResourceNotFoundException("Only interviewed candidates will be selected");
            }

            a.setStatus(ApplicationStatus.SELECTED);
            Application saved=applicationRepository.save(a);
            ApplicationResponse res=new ApplicationResponse();

            res.setJobId(saved.getJob().getId());
            res.setId(saved.getId());
            res.setStatus(saved.getStatus());
            res.setStudentId(saved.getStudent().getId());
            res.setAppliedAt(saved.getAppliedAt());
            return res;
        }
        else{
            throw new ResourceNotFoundException("Application not found with the id "+id);
        }
    }

    public ApplicationResponse rejectApplication(Long id){
        Optional<Application> app=applicationRepository.findById(id);

        if(app.isPresent()){
            Application a=app.get();
            if(a.getStatus()!=ApplicationStatus.INTERVIEW){
                throw new ResourceNotFoundException("Only interviewed candidates will be rejected");
            }

            a.setStatus(ApplicationStatus.REJECTED);
            Application saved=applicationRepository.save(a);
            ApplicationResponse res=new ApplicationResponse();

            res.setJobId(saved.getJob().getId());
            res.setId(saved.getId());
            res.setStatus(saved.getStatus());
            res.setStudentId(saved.getStudent().getId());
            res.setAppliedAt(saved.getAppliedAt());
            return res;
        }
        else{
            throw new ResourceNotFoundException("Application not found with the id "+id);
        }
    }
}
