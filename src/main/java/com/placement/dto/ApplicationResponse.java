package com.placement.dto;

import com.placement.entity.ApplicationStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApplicationResponse {
    private Long id;

    private Long studentId;

    private Long jobId;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}
