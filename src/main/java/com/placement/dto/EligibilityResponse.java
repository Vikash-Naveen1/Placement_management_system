package com.placement.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EligibilityResponse {
    private Long id;

    private BigDecimal minimumTenthPercentage;

    private BigDecimal minimumTwelfthPercentage;

    private BigDecimal minimumCgpa;

    private Integer maximumBacklogs;

    private Integer eligibleFromYear;

    private Integer eligibleToYear;
}