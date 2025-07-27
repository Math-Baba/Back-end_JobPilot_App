package com.project.jobpilot.requests;

import com.project.jobpilot.enums.CompanyType;
import com.project.jobpilot.enums.PositionType;
import com.project.jobpilot.enums.Sector;
import com.project.jobpilot.enums.Status;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Builder
@Value
public class JobApplicationFilterRequest {
    List<PositionType> positionType;
    List<Status> status;
    List<Sector> sector;
    List<CompanyType> companyType;
}
