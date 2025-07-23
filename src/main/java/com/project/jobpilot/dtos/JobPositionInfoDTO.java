package com.project.jobpilot.dtos;

import com.project.jobpilot.enums.PositionType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class JobPositionInfoDTO {
    Long id;
    String jobTitle;
    PositionType positionType;
    LocalDate applicationDate;
    String description;
}
