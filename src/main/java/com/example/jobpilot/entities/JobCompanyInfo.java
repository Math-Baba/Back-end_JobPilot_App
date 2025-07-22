package com.example.jobpilot.entities;

import com.example.jobpilot.enums.CompanyType;
import com.example.jobpilot.enums.Sector;
import jakarta.persistence.*;
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
    @NotBlank
    String email;
    String phone;
    @Enumerated(EnumType.STRING)
    CompanyType companyType;
}