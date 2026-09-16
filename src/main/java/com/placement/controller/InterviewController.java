package com.placement.controller;

import com.placement.dto.InterviewRequest;
import com.placement.dto.InterviewResponse;
import com.placement.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<InterviewResponse> createInterview(@Valid @RequestBody InterviewRequest req) {
        return new ResponseEntity<>(interviewService.createInterview(req), HttpStatus.CREATED);
    }
}