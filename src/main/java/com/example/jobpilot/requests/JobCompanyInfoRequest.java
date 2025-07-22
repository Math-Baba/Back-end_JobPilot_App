package com.example.jobpilot.requests;

import com.example.jobpilot.enums.CompanyType;
import com.example.jobpilot.enums.Sector;
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
    CompanyType companyType;
}
