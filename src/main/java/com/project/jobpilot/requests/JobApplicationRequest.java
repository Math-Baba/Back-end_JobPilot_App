package com.project.jobpilot.requests;

import lombok.Builder;
import lombok.Value;

/**
 * Classe Request modélisant la structure générale (formulaire)
 * Que le client envoie au serveur
 */
@Builder
@Value
public class JobApplicationRequest {
    Long id;
    JobCompanyInfoRequest jobCompanyInfo;
    JobPositionInfoRequest jobPositionInfo;
    String notes;
}
