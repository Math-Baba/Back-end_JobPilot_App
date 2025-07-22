package com.example.jobpilot.responses;

import com.example.jobpilot.enums.CompanyType;
import com.example.jobpilot.enums.Sector;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Value
public class JobApplicationResponse {
    Long id;
    String companyName;
    String jobTitle;
    Sector sector;
    String companyAdress;
    LocalDate applicationDate;
    CompanyType companyType;
}
