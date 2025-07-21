package com.example.jobpilot.repositories;

import com.example.jobpilot.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}
