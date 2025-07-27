package com.project.jobpilot.responses;

import com.project.jobpilot.enums.CompanyType;
import com.project.jobpilot.enums.PositionType;
import com.project.jobpilot.enums.Sector;
import com.project.jobpilot.enums.Status;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Value
public class JobApplicationResponse {
    Long id;
    String companyName;
    String email;
    String jobTitle;
    Sector sector;
    Status status;
    PositionType positionType;
    String companyAdress;
    LocalDate applicationDate;
    CompanyType companyType;
}
