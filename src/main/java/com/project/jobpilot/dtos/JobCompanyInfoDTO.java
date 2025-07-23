package com.project.jobpilot.dtos;

import com.project.jobpilot.enums.CompanyType;
import com.project.jobpilot.enums.Sector;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class JobCompanyInfoDTO {
    Long id;
    String name;
    Sector sector;
    String adress;
    String email;
    String phone;
    CompanyType companyType;
}
