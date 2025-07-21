package com.example.jobpilot.dtos;

import com.example.jobpilot.enums.PositionType;

import java.time.LocalDate;

public class JobPositionInfoDTO {
    Long id;
    String jobTitle;
    PositionType positionType;
    LocalDate applicationDate;
    String description;
}
