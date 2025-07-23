package com.project.jobpilot.responses;

import com.project.jobpilot.enums.CompanyType;
import com.project.jobpilot.enums.Sector;
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
