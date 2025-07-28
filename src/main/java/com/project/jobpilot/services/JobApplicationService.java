package com.project.jobpilot.services;

import com.project.jobpilot.dtos.JobApplicationDTO;
import com.project.jobpilot.entities.JobApplication;
import com.project.jobpilot.mappers.JobApplicationMapper;
import com.project.jobpilot.repositories.JobApplicationRepository;
import com.project.jobpilot.requests.JobApplicationFilterRequest;
import com.project.jobpilot.requests.JobApplicationRequest;
import com.project.jobpilot.responses.JobApplicationResponse;
import com.project.jobpilot.specification.JobApplicationSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service de gestion des Candidatures
 * Centralise la logique métier de l'application (CRUD, filtre, recherche)
 */
@RequiredArgsConstructor
@Service
@Validated
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationMapper jobApplicationMapper;

    @Transactional
    public List<JobApplicationResponse> getAllJobApplications(){
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        if(jobApplicationList.isEmpty()){
            throw new EntityNotFoundException("Aucune candidature trouvée");
        }
        return jobApplicationList.stream()
                .map(jobApplicationMapper::jobApplicationToJobApplicationResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createJobApplication(JobApplicationRequest jobApplicationRequest){
        JobApplication jobCreated = jobApplicationMapper.jobApplicationRequestToJobApplication(jobApplicationRequest);
        jobApplicationRepository.save(jobCreated);
    }

    @Transactional
    public void updateJobApplication(JobApplicationRequest jobApplicationRequest){
        JobApplication jobUpdated = jobApplicationMapper.jobApplicationRequestToJobApplication(jobApplicationRequest);
        jobApplicationRepository.save(jobUpdated);
    }

    @Transactional
    public void deleteJobApplication(Long Id){
        jobApplicationRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("La candidature avec l'id " + Id + " n'existe pas."));
        jobApplicationRepository.deleteById(Id);
    }

    @Transactional
    public JobApplicationDTO getJobApplicationById(long Id){
        return jobApplicationRepository.findById(Id)
                .map(jobApplicationMapper::jobApplicationToJobApplicationDTO)
                .orElseThrow(() -> new EntityNotFoundException("La candidature avec l'id " + Id + " n'existe pas."));
    }

    @Transactional
    public List<JobApplicationResponse> search(String query) {
        List<JobApplication> jobApplications = jobApplicationRepository
                .findByJobCompanyInfo_NameContainingIgnoreCaseOrJobPositionInfo_JobTitleContainingIgnoreCase(query, query);

        return jobApplications.stream()
                .map(jobApplicationMapper::jobApplicationToJobApplicationResponse)
                .toList();
    }

    @Transactional
    public List<JobApplicationResponse> filter(JobApplicationFilterRequest filterRequest) {
        Specification<JobApplication> spec = null;

        if (filterRequest.getPositionType() != null && !filterRequest.getPositionType().isEmpty()) {
            spec = JobApplicationSpecification.hasPositionType(filterRequest.getPositionType());
        }

        if (filterRequest.getStatus() != null && !filterRequest.getStatus().isEmpty()) {
            Specification<JobApplication> statusSpec = JobApplicationSpecification.hasStatus(filterRequest.getStatus());
            spec = (spec == null) ? statusSpec : spec.and(statusSpec);
        }

        if (filterRequest.getSector() != null && !filterRequest.getSector().isEmpty()) {
            Specification<JobApplication> sectorSpec = JobApplicationSpecification.hasSector(filterRequest.getSector());
            spec = (spec == null) ? sectorSpec : spec.and(sectorSpec);
        }

        if (filterRequest.getCompanyType() != null && !filterRequest.getCompanyType().isEmpty()) {
            Specification<JobApplication> companyTypeSpec = JobApplicationSpecification.hasCompanyType(filterRequest.getCompanyType());
            spec = (spec == null) ? companyTypeSpec : spec.and(companyTypeSpec);
        }

        List<JobApplication> results = (spec == null)
                ? jobApplicationRepository.findAll()
                : jobApplicationRepository.findAll(spec);

        return results.stream()
                .map(jobApplicationMapper::jobApplicationToJobApplicationResponse)
                .collect(Collectors.toList());
    }
}
