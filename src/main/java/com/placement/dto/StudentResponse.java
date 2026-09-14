package com.placement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class StudentResponse {
    private Long id;

    private String name;

    private String email;

    private LocalDate dateOfBirth;

    private Integer joiningYear;

    private Integer graduationYear;

    private BigDecimal cgpa;

    private BigDecimal tenthPercentage;

    private BigDecimal twelfthPercentage;

    private Integer backlogs;

    private Integer currentSemester;

    private String resumeUrl;

}

