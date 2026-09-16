package com.placement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewRequest {

    @NotNull
    private Long applicationId;

    @NotNull
    private LocalDateTime scheduledAt;

    @NotBlank
    private String round;
}