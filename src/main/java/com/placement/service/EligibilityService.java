package com.placement.service;

import com.placement.dto.EligibilityRequest;
import com.placement.dto.EligibilityResponse;
import com.placement.entity.Eligibility;
import com.placement.repository.EligibilityRepository;
import org.springframework.stereotype.Service;

@Service
public class EligibilityService {

    private final EligibilityRepository eligibilityRepository;

    public EligibilityService(EligibilityRepository eligibilityRepository){
        this.eligibilityRepository=eligibilityRepository;
    }

    public EligibilityResponse createEligibility(EligibilityRequest req){
        Eligibility eligibility=new Eligibility();

        eligibility.setMinimumTenthPercentage(req.getMinimumTenthPercentage());
        eligibility.setMinimumTwelfthPercentage(req.getMinimumTwelfthPercentage());
        eligibility.setEligibleFromYear(req.getEligibleFromYear());
        eligibility.setEligibleToYear(req.getEligibleToYear());
        eligibility.setMinimumCgpa(req.getMinimumCgpa());
        eligibility.setMaximumBacklogs(req.getMaximumBacklogs());

        Eligibility saved=eligibilityRepository.save(eligibility);

        EligibilityResponse res=new EligibilityResponse();
        res.setId(saved.getId());
        res.setMinimumTenthPercentage(saved.getMinimumTenthPercentage());
        res.setMinimumTwelfthPercentage(saved.getMinimumTwelfthPercentage());
        res.setEligibleFromYear(saved.getEligibleFromYear());
        res.setEligibleToYear(saved.getEligibleToYear());
        res.setMinimumCgpa(saved.getMinimumCgpa());
        res.setMaximumBacklogs(saved.getMaximumBacklogs());

        return res;
    }

}
