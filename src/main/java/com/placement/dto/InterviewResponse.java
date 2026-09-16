package com.placement.dto;

import com.placement.entity.InterviewStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewResponse {

    private Long id;

    private Long applicationId;

    private LocalDateTime scheduledAt;

    private String round;

    private InterviewStatus status;

    private String feedback;
}