package com.project.jobpilot.dtos;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class JobApplicationDTO {
    Long id;
    JobCompanyInfoDTO jobCompanyInfo;
    JobPositionInfoDTO jobPositionInfo;
    String notes;
}
