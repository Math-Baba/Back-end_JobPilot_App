package com.project.jobpilot.services;

import com.project.jobpilot.dtos.JobApplicationDTO;
import com.project.jobpilot.entities.JobApplication;
import com.project.jobpilot.mappers.JobApplicationMapper;
import com.project.jobpilot.repositories.JobApplicationRepository;
import com.project.jobpilot.requests.JobApplicationRequest;
import com.project.jobpilot.responses.JobApplicationResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

}
