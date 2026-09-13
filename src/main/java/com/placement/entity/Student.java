package com.placement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(length=100, nullable=false)
    private String name;

    @NotBlank
    @Email
    @Column(length=255, nullable=false, unique=true)
    private String email;

    @NotNull
    @Column(nullable=false)
    private LocalDate dateOfBirth;

    @NotNull
    @Column(nullable=false)
    private Integer joiningYear;

    @NotNull
    @Column(nullable=false)
    private Integer graduationYear;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(precision=4, scale=2, nullable=false)
    private BigDecimal cgpa;

    @NotNull
    @Column(precision=5,scale=2,nullable=false)
    private BigDecimal tenthPercentage;

    @NotNull
    @Column(precision=5,scale=2,nullable=false)
    private BigDecimal twelfthPercentage;

    @NotNull
    @Column(nullable=false)
    @Min(0)
    private Integer backlogs;

    @NotNull
    @Column(nullable=false)
    @Min(1)
    @Max(8)
    private Integer currentSemester;

    @Column(length=500)
    private String resumeUrl;

}

