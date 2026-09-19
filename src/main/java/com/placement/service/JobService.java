package com.placement.service;

import com.placement.dto.JobRequest;
import com.placement.dto.JobResponse;
import com.placement.entity.Company;
import com.placement.entity.Job;
import com.placement.entity.JobStatus;
import com.placement.exception.BusinessException;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.CompanyRepository;
import com.placement.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;

    public JobService(CompanyRepository companyRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
    }

    public JobResponse createJob(JobRequest req){
        Optional<Company> company=companyRepository.findById(req.getCompanyId());

        if(company.isPresent()){
            Job j=new Job();
            j.setCompany(company.get());
            j.setTitle(req.getTitle());
            j.setDescription(req.getDescription());
            j.setLocation(req.getLocation());
            j.setAnnualCtcLpa(req.getAnnualCtcLpa());
            j.setApplicationDeadline(req.getApplicationDeadline());
            j.setStatus(JobStatus.PENDING_APPROVAL);

            Job saved=jobRepository.save(j);

            JobResponse res = new JobResponse();

            res.setId(saved.getId());
            res.setCompanyId(saved.getCompany().getId());
            res.setTitle(saved.getTitle());
            res.setDescription(saved.getDescription());
            res.setLocation(saved.getLocation());
            res.setAnnualCtcLpa(saved.getAnnualCtcLpa());
            res.setApplicationDeadline(saved.getApplicationDeadline());
            res.setStatus(saved.getStatus());

            return res;
        }
        else{
            throw new ResourceNotFoundException("Company not found with Id "+req.getCompanyId());
        }
    }

    public JobResponse getJobById(Long id){
        Optional<Job> job=jobRepository.findById(id);

        if(job.isPresent()){
            Job j=job.get();
            JobResponse res=new JobResponse();

            res.setId(j.getId());
            res.setCompanyId(j.getCompany().getId());
            res.setTitle(j.getTitle());
            res.setDescription(j.getDescription());
            res.setLocation(j.getLocation());
            res.setAnnualCtcLpa(j.getAnnualCtcLpa());
            res.setApplicationDeadline(j.getApplicationDeadline());
            res.setStatus(j.getStatus());

            return res;
        }
        else{
            throw new ResourceNotFoundException("Job not found with that Id "+id);
        }
    }

    public List<JobResponse> getAll(){
        List<Job> job=jobRepository.findAll();
        List<JobResponse> response=new ArrayList<>();
        int i=0;
        while(i<job.size()){
            Job j=job.get(i);
            JobResponse res=new JobResponse();

            res.setId(j.getId());
            res.setCompanyId(j.getCompany().getId());
            res.setTitle(j.getTitle());
            res.setDescription(j.getDescription());
            res.setLocation(j.getLocation());
            res.setAnnualCtcLpa(j.getAnnualCtcLpa());
            res.setApplicationDeadline(j.getApplicationDeadline());
            res.setStatus(j.getStatus());

            response.add(res);
            i++;
        }
        return response;
    }

    public JobResponse updateJobById(Long id,JobRequest req){
        Optional<Job> job=jobRepository.findById(id);

        if(job.isPresent()){
            Job j=job.get();
            Optional<Company> company=companyRepository.findById(req.getCompanyId());

            if(company.isPresent()){
                j.setCompany(company.get());
                j.setTitle(req.getTitle());
                j.setDescription(req.getDescription());
                j.setLocation(req.getLocation());
                j.setAnnualCtcLpa(req.getAnnualCtcLpa());
                j.setApplicationDeadline(req.getApplicationDeadline());

                Job updated=jobRepository.save(j);

                JobResponse res=new JobResponse();

                res.setId(updated.getId());
                res.setCompanyId(updated.getCompany().getId());
                res.setTitle(updated.getTitle());
                res.setDescription(updated.getDescription());
                res.setLocation(updated.getLocation());
                res.setAnnualCtcLpa(updated.getAnnualCtcLpa());
                res.setApplicationDeadline(updated.getApplicationDeadline());
                res.setStatus(updated.getStatus());

                return res;
            }
            else{
                throw new ResourceNotFoundException("Company not found with Id " + req.getCompanyId());
            }
        }
        else{
            throw new ResourceNotFoundException("Job not found with Id " + id);
        }
    }

    public void deleteJob(Long id){
        Optional<Job> job=jobRepository.findById(id);

        if(job.isPresent()){
            jobRepository.delete(job.get());
        }
        else{
            throw new ResourceNotFoundException("Job not found with id "+id);
        }
    }

    public JobResponse approvalJob(Long id){
        Optional<Job> job=jobRepository.findById(id);

        if(job.isPresent()){
            Job j=job.get();
            if(j.getStatus()!=JobStatus.PENDING_APPROVAL){
                throw new BusinessException("Only pending jobs can be approved");
            }
            j.setStatus(JobStatus.OPEN);

            Job updated = jobRepository.save(j);

            JobResponse res = new JobResponse();

            res.setId(updated.getId());
            res.setCompanyId(updated.getCompany().getId());
            res.setTitle(updated.getTitle());
            res.setDescription(updated.getDescription());
            res.setLocation(updated.getLocation());
            res.setAnnualCtcLpa(updated.getAnnualCtcLpa());
            res.setApplicationDeadline(updated.getApplicationDeadline());
            res.setStatus(updated.getStatus());

            return res;
        }
        else {
            throw new ResourceNotFoundException("Job not found with id " + id);
        }
    }

    public JobResponse rejectJob(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job j = job.get();
            if (j.getStatus() != JobStatus.PENDING_APPROVAL) {
                throw new BusinessException("Job is not opened yet");
            }

            j.setStatus(JobStatus.REJECTED);

            Job updated = jobRepository.save(j);

            JobResponse res = new JobResponse();

            res.setId(updated.getId());
            res.setCompanyId(updated.getCompany().getId());
            res.setTitle(updated.getTitle());
            res.setDescription(updated.getDescription());
            res.setLocation(updated.getLocation());
            res.setAnnualCtcLpa(updated.getAnnualCtcLpa());
            res.setApplicationDeadline(updated.getApplicationDeadline());
            res.setStatus(updated.getStatus());

            return res;

        } else {
            throw new ResourceNotFoundException("Job not found with id " + id);
        }
    }

    public Page<JobResponse> getAllJobsPages(int page,int size){
        Pageable pageable = PageRequest.of(page,size,Sort.by("annualCtclpa").descending());
        Page<Job> jobs= jobRepository.findAll(pageable);
        return jobs.map(job->{
            JobResponse res=new JobResponse();
            res.setId(job.getId());
            res.setCompanyId(job.getCompany().getId());
            res.setTitle(job.getTitle());
            res.setDescription(job.getDescription());
            res.setLocation(job.getLocation());
            res.setAnnualCtcLpa(job.getAnnualCtcLpa());
            res.setApplicationDeadline(job.getApplicationDeadline());
            res.setStatus(job.getStatus());

            return res;
        });
    }
}
