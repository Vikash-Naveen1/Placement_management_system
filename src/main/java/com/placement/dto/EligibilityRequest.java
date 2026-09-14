package com.placement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EligibilityRequest {
    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.00")
    private BigDecimal minimumTenthPercentage;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.00")
    private BigDecimal minimumTwelfthPercentage;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private BigDecimal minimumCgpa;

    @NotNull
    @Min(0)
    private Integer maximumBacklogs;

    @NotNull
    private Integer eligibleFromYear;

    @NotNull
    private Integer eligibleToYear;
}