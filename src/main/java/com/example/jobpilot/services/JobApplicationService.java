package com.example.jobpilot.services;

import com.example.jobpilot.entities.JobApplication;
import com.example.jobpilot.repositories.JobApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RequiredArgsConstructor
@Service
@Validated
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;

    @Transactional
    public List<JobApplication> getAllJobApplications(){
        return jobApplicationRepository.findAll();
    }

    @Transactional
    public void createJobApplication(JobApplication jobApplication){
        jobApplicationRepository.save(jobApplication);
    }

    @Transactional
    public void updateJobApplication(JobApplication jobApplication){
        jobApplicationRepository.findById(jobApplication.getId());
        jobApplicationRepository.save(jobApplication);
    }

    @Transactional
    public void deleteJobApplication(Long Id){
        jobApplicationRepository.deleteById(Id);
    }
}
