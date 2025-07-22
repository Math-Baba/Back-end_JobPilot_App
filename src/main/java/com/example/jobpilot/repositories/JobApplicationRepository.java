package com.example.jobpilot.repositories;

import com.example.jobpilot.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByJobCompanyInfo_NameContainingIgnoreCaseOrJobPositionInfo_JobTitleContainingIgnoreCase(String name, String jobTitle);
}
