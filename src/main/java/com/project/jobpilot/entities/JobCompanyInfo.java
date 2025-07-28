package com.project.jobpilot.entities;

import com.project.jobpilot.enums.CompanyType;
import com.project.jobpilot.enums.Sector;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobCompanyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String name;
    @Enumerated(EnumType.STRING)
    Sector sector;
    String adress;
    @Email
    @NotBlank
    String email;
    String phone;
    String website;
    @Enumerated(EnumType.STRING)
    CompanyType companyType;
}