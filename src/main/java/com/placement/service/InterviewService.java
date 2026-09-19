package com.placement.service;

import com.placement.dto.InterviewRequest;
import com.placement.dto.InterviewResponse;
import com.placement.entity.Application;
import com.placement.entity.ApplicationStatus;
import com.placement.entity.Interview;
import com.placement.entity.InterviewStatus;
import com.placement.exception.BusinessException;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.ApplicationRepository;
import com.placement.repository.InterviewRepository;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;

    public InterviewService(InterviewRepository interviewRepository, ApplicationRepository applicationRepository) {
        this.interviewRepository = interviewRepository;
        this.applicationRepository = applicationRepository;
    }

    public InterviewResponse createInterview(InterviewRequest req) {

        // 1. Find application
        Application application = applicationRepository
                .findById(req.getApplicationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with id "
                                        + req.getApplicationId()
                        ));

        // 2. Application must be shortlisted
        if (application.getStatus() != ApplicationStatus.SHORTLISTED) {
            throw new BusinessException(
                    "Only shortlisted applications can be interviewed"
            );
        }

        // 3. Create interview
        Interview interview = new Interview();

        interview.setApplication(application);
        interview.setScheduledAt(req.getScheduledAt());
        interview.setRound(req.getRound());
        interview.setStatus(InterviewStatus.SCHEDULED);

        // 4. Save interview
        Interview saved = interviewRepository.save(interview);

        // 5. Update application status
        application.setStatus(ApplicationStatus.INTERVIEW);
        applicationRepository.save(application);

        // 6. Entity -> Response DTO
        InterviewResponse res = new InterviewResponse();

        res.setId(saved.getId());
        res.setApplicationId(saved.getApplication().getId());
        res.setScheduledAt(saved.getScheduledAt());
        res.setRound(saved.getRound());
        res.setStatus(saved.getStatus());
        res.setFeedback(saved.getFeedback());

        return res;
    }
}