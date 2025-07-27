package com.project.jobpilot.service;

import com.project.jobpilot.dtos.JobApplicationDTO;
import com.project.jobpilot.entities.JobApplication;
import com.project.jobpilot.entities.JobCompanyInfo;
import com.project.jobpilot.entities.JobPositionInfo;
import com.project.jobpilot.repositories.JobApplicationRepository;
import com.project.jobpilot.requests.JobApplicationFilterRequest;
import com.project.jobpilot.requests.JobApplicationRequest;
import com.project.jobpilot.requests.JobCompanyInfoRequest;
import com.project.jobpilot.requests.JobPositionInfoRequest;
import com.project.jobpilot.responses.JobApplicationResponse;
import com.project.jobpilot.services.JobApplicationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.project.jobpilot.enums.CompanyType.STARTUP;
import static com.project.jobpilot.enums.Sector.AI_DATA;
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
        JobApplicationRequest createdJob = JobApplicationRequest.builder()
                .notes("Candidature test")
                .build();

        jobApplicationService.createJobApplication(createdJob);
        assertThat(jobApplicationRepository.findAll()).isNotEmpty();
    }

    @Test
    public void getAllJobApplication_shouldReturnJobApplications(){
        jobApplicationRepository.save(JobApplication.builder()
                .jobCompanyInfo(JobCompanyInfo.builder()
                        .name("Data Corp")
                        .email("datascience@gmail.com")
                        .adress("Rue")
                        .companyType(STARTUP)
                        .build())
                .jobPositionInfo(JobPositionInfo.builder()
                        .jobTitle("Data analyst")
                        .applicationDate(LocalDate.of(2025, 7, 22))
                        .build())
                .notes("Candidature test")
                .build());

        List<JobApplicationResponse> jobApplicationList = jobApplicationService.getAllJobApplications();
        assertThat(jobApplicationList).hasSize(1);
    }

    @Test
    public void editJobApplication_shouldUpdateJobApplication(){
        JobApplication save = jobApplicationRepository.save(JobApplication.builder()
                .jobCompanyInfo(JobCompanyInfo.builder()
                        .name("Data Corp")
                        .email("datascience@gmail.com")
                        .adress("Rue")
                        .companyType(STARTUP)
                        .build())
                .jobPositionInfo(JobPositionInfo.builder()
                        .jobTitle("Data analyst")
                        .applicationDate(LocalDate.of(2025, 7, 22))
                        .build())
                .notes("Candidature test")
                .build());

        JobApplicationRequest update = JobApplicationRequest.builder()
                .id(save.getId())
                .jobCompanyInfo(JobCompanyInfoRequest.builder()
                        .id(save.getJobCompanyInfo().getId())
                        .name("DataScience Corp")
                        .email("datascience@gmail.com")
                        .build())
                .jobPositionInfo(JobPositionInfoRequest.builder()
                        .jobTitle("Data analyst")
                        .applicationDate(LocalDate.of(2025, 7, 22))
                        .build())
                .notes("Candidature update")
                .build();

        jobApplicationService.updateJobApplication(update);
        List<JobApplicationResponse> jobApplicationList = jobApplicationService.getAllJobApplications();
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

    @Test
    public void search_shouldReturnMatchingJobApplication(){
        jobApplicationRepository.save(JobApplication.builder()
                .jobCompanyInfo(JobCompanyInfo.builder()
                        .name("Data Corp")
                        .email("datascience@gmail.com")
                        .adress("Rue")
                        .sector(AI_DATA)
                        .companyType(STARTUP)
                        .build())
                .jobPositionInfo(JobPositionInfo.builder()
                        .jobTitle("Data analyst")
                        .applicationDate(LocalDate.of(2025, 7, 22))
                        .build())
                .notes("Candidature test")
                .build());

        List<JobApplicationResponse> results = jobApplicationService.search("Data");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getCompanyName()).isEqualTo("Data Corp");

        List<JobApplicationResponse> results3 = jobApplicationService.search("analyst");
        assertThat(results3).hasSize(1);
        assertThat(results3.get(0).getJobTitle()).isEqualTo("Data analyst");
    }

    @Test
    public void getCompanyById_shouldReturnCompany_ifCompanyExists() {
        JobApplication job = jobApplicationRepository.save(JobApplication.builder().build());
        JobApplicationDTO jobApplicationDTO = jobApplicationService.getJobApplicationById(job.getId());
        assertThat(jobApplicationDTO.getId()).isEqualTo(job.getId());
        assertThat(jobApplicationDTO).usingRecursiveComparison().isEqualTo(job);
    }

    @Test
    public void filterJobApplication_shouldReturnJobApplicationIfExists(){
        jobApplicationRepository.save(JobApplication.builder()
                .jobCompanyInfo(JobCompanyInfo.builder()
                        .name("Data Corp")
                        .email("datascience@gmail.com")
                        .adress("Rue")
                        .sector(AI_DATA)
                        .companyType(STARTUP)
                        .build())
                .jobPositionInfo(JobPositionInfo.builder()
                        .jobTitle("Data analyst")
                        .applicationDate(LocalDate.of(2025, 7, 22))
                        .build())
                .notes("Candidature test")
                .build());

        JobApplicationFilterRequest filterRequest = JobApplicationFilterRequest.builder()
                .sector(List.of(AI_DATA))
                .build();

        List<JobApplicationResponse> jobs = jobApplicationService.filter(filterRequest);
        assertThat(jobs).hasSize(1);
    }
}
