package com.placement.dto;

import com.placement.entity.JobStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class JobResponse {

    private Long id;

    private Long companyId;

    private String title;

    private String description;

    private String location;

    private BigDecimal annualCtcLpa;

    private LocalDate applicationDeadline;

    private JobStatus status;
}