package com.project.jobpilot.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class JobApplicationRequest {
    Long id;
    JobCompanyInfoRequest jobCompanyInfo;
    JobPositionInfoRequest jobPositionInfo;
    String notes;
}
