package com.example.jobpilot.dtos;

import com.example.jobpilot.entities.JobCompanyInfo;
import com.example.jobpilot.entities.JobPositionInfo;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class JobApplicationDTO {
    Long id;
    JobCompanyInfo jobCompanyInfo;
    JobPositionInfo jobPositionInfo;
    String notes;
}
