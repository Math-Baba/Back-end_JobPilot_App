package com.example.jobpilot.dtos;

import com.example.jobpilot.enums.CompanyType;
import com.example.jobpilot.enums.Sector;

public class JobCompanyInfoDTO {
    Long id;
    String name;
    Sector sector;
    String adress;
    String email;
    String phone;
    CompanyType companyType;
}
