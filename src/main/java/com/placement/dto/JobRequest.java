package com.placement.dto;

import com.placement.entity.Company;
import com.placement.entity.JobStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class JobRequest {

    @NotNull
    private Long companyId;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String location;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal annualCtcLpa;

    @NotNull
    private LocalDate applicationDeadline;
}