package com.project.jobpilot.dtos;

import com.project.jobpilot.enums.PositionType;
import com.project.jobpilot.enums.Status;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class JobPositionInfoDTO {
    Long id;
    String jobTitle;
    Status status;
    PositionType positionType;
    LocalDate applicationDate;
    String description;
}
