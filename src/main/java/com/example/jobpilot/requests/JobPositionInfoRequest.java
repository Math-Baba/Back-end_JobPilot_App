package com.example.jobpilot.requests;

import com.example.jobpilot.enums.PositionType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class JobPositionInfoRequest {
    Long id;
    String jobTitle;
    PositionType positionType;
    LocalDate applicationDate;
    String description;
}
