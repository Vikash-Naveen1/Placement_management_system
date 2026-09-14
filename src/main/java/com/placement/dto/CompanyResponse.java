package com.placement.dto;
import lombok.Data;

@Data
public class CompanyResponse {
    private Long id;

    private String name;

    private String location;

    private String email;

    private String description;

    private String website;
}
