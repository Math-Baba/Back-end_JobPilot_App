package com.project.jobpilot.requests;

import com.project.jobpilot.enums.CompanyType;
import com.project.jobpilot.enums.PositionType;
import com.project.jobpilot.enums.Sector;
import com.project.jobpilot.enums.Status;
import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * Classe Request modélisant la structure du filtre
 * Que le client envoie au serveur
 */
@Builder
@Value
public class JobApplicationFilterRequest {
    List<PositionType> positionType;
    List<Status> status;
    List<Sector> sector;
    List<CompanyType> companyType;
}
