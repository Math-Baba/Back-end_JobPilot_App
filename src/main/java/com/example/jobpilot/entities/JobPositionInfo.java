package com.example.jobpilot.entities;

import com.example.jobpilot.enums.PositionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobPositionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String jobTitle;
    @Enumerated(EnumType.STRING)
    PositionType positionType;
    @NotNull
    LocalDate applicationDate;
    String description;
}