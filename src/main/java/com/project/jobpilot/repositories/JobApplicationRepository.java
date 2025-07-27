package com.project.jobpilot.repositories;

import com.project.jobpilot.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>, JpaSpecificationExecutor<JobApplication> {
    List<JobApplication> findByJobCompanyInfo_NameContainingIgnoreCaseOrJobPositionInfo_JobTitleContainingIgnoreCase(String name, String jobTitle);
}
