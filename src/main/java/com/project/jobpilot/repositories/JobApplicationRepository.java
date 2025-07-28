package com.project.jobpilot.repositories;

import com.project.jobpilot.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Interface qui centralise la gestion des données.
 * Permet d'éviter d'écrire directement des requêtes SQL.
 */
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>, JpaSpecificationExecutor<JobApplication> {
    /**
     * Recherche les candidatures dont le nom de l'entreprise ou l'intitulé du poste
     * contiennent une chaîne donnée (sans tenir compte de la casse).
     * @param name
     * @param jobTitle
     * @return
     */
    List<JobApplication> findByJobCompanyInfo_NameContainingIgnoreCaseOrJobPositionInfo_JobTitleContainingIgnoreCase(String name, String jobTitle);
}
