package com.placement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Eligibility {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.00")
    @Column(precision=5,scale=2,nullable=false)
    private BigDecimal minimumTenthPercentage;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.00")
    @Column(precision=5,scale=2,nullable=false)
    private BigDecimal minimumTwelfthPercentage;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(precision=3,scale=2,nullable=false)
    private BigDecimal minimumCgpa;

    @NotNull
    @Min(0)
    @Column(nullable=false)
    private Integer maximumBacklogs;

    @NotNull
    @Column(nullable=false)
    private Integer eligibleFromYear;

    @NotNull
    @Column(nullable=false)
    private Integer eligibleToYear;
}