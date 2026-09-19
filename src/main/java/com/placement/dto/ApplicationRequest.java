package com.placement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationRequest {
    @NotNull
    private Long studentId;

    @NotNull
    private Long jobId;

}
