package com.project.jobpilot.requests;

import com.project.jobpilot.enums.CompanyType;
import com.project.jobpilot.enums.Sector;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class JobCompanyInfoRequest {
    Long id;
    String name;
    Sector sector;
    String adress;
    String email;
    String phone;
    String website;
    CompanyType companyType;
}
