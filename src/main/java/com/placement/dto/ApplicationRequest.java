package com.placement.dto;

import com.placement.entity.Student;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationRequest {
    @NotNull
    private Long studentId;

    @NotNull
    private Long jobId;

}
