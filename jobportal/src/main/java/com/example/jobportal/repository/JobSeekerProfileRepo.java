package com.example.jobportal.repository;

import com.example.jobportal.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepo extends JpaRepository<JobSeekerProfile, Integer> {
}
