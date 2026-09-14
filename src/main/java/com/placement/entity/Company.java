package com.placement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(length=255,unique=true,nullable=false)
    private String name;

    @NotBlank
    @Column(length=100,nullable=false)
    private String location;

    @NotBlank
    @Email
    @Column(length=255,unique=true,nullable=false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length=255)
    private String website;

//    public String getDescription(String description) {
//        return description;
//    }
}
