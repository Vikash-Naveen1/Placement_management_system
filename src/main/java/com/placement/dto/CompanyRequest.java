package com.placement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotBlank
    @Email
    private String email;

    private String description;

    private String website;
}
