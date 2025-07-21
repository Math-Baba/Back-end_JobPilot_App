package com.example.jobpilot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne(cascade = CascadeType.ALL)
    JobCompanyInfo jobCompanyInfo;
    @OneToOne(cascade = CascadeType.ALL)
    JobPositionInfo jobPositionInfo;
    String notes;
}
