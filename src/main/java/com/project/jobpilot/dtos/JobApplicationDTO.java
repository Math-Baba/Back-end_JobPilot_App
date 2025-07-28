package com.project.jobpilot.dtos;

import lombok.Builder;
import lombok.Value;

/**
 * Classe DTO destiné au transfert de données
 * structure complète de l'objet
 */
@Builder
@Value
public class JobApplicationDTO {
    Long id;
    JobCompanyInfoDTO jobCompanyInfo;
    JobPositionInfoDTO jobPositionInfo;
    String notes;
}
