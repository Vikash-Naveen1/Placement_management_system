package com.placement.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class StudentRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Integer joiningYear;

    @NotNull
    private Integer graduationYear;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private BigDecimal cgpa;

    @NotNull
    private BigDecimal tenthPercentage;

    @NotNull
    private BigDecimal twelfthPercentage;

    @NotNull
    @Min(0)
    private Integer backlogs;

    @NotNull
    @Min(1)
    @Max(8)
    private Integer currentSemester;
    
    private String resumeUrl;

}

