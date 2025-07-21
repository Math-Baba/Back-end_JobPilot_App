package com.example.jobpilot.service;

import com.example.jobpilot.entities.JobApplication;
import com.example.jobpilot.entities.JobCompanyInfo;
import com.example.jobpilot.repositories.JobApplicationRepository;
import com.example.jobpilot.services.JobApplicationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JobApplicationServiceIT {

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @AfterEach
    public void tearDown() {
        jobApplicationRepository.deleteAll();
    }

    @Test
    public void createJobApplication_shouldSaveJobApplication(){
        JobApplication createdJob = JobApplication.builder()
                .notes("Candidature test")
                .build();

        jobApplicationService.createJobApplication(createdJob);

        assertThat(jobApplicationRepository.findAll()).isNotEmpty();
    }

    @Test
    public void getAllJobApplication_shouldReturnJobApplications(){
        JobApplication.builder()
                .notes("Candidature test")
                .build();

        List<JobApplication> jobApplicationList = jobApplicationService.getAllJobApplications();

        assertThat(jobApplicationList).hasSize(1);
    }

    @Test
    public void editJobApplication_shouldUpdateJobApplication(){
        JobApplication save = JobApplication.builder()
                .jobCompanyInfo(JobCompanyInfo.builder()
                        .name("Data Corp")
                        .email("datascience@gmail.com")
                        .build())
                .notes("Candidature test")
                .build();

        jobApplicationService.createJobApplication(save);

        JobApplication update = JobApplication.builder()
                .id(save.getId())
                .jobCompanyInfo(JobCompanyInfo.builder()
                        .id(save.getJobCompanyInfo().getId())
                        .name("DataScience Corp")
                        .email("datascience@gmail.com")
                        .build())
                .notes("Candidature update")
                .build();

        jobApplicationService.updateJobApplication(update);
        List<JobApplication> jobApplicationList = jobApplicationService.getAllJobApplications();
        Optional<JobApplication> jobApplicationUpdated = jobApplicationRepository.findById(save.getId());

        assertThat(jobApplicationUpdated).isPresent();
        assertThat(jobApplicationList).hasSize(1);
        assertThat(jobApplicationUpdated.get().getJobCompanyInfo().getName()).isEqualTo("DataScience Corp");
        assertThat(jobApplicationUpdated.get().getNotes()).isEqualTo("Candidature update");
    }

    @Test
    public void deleteJobApplication_shouldDeleteJobApplication(){
        JobApplication job =  jobApplicationRepository.save(JobApplication.builder().build());
        jobApplicationService.deleteJobApplication(job.getId());
        assertThat(jobApplicationRepository.findById(job.getId())).isEmpty();
    }
}
