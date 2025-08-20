package com.example.jobportal.services;

import com.example.jobportal.entity.JobPostActivity;
import com.example.jobportal.repository.JobPostActivityRepo;
import org.springframework.stereotype.Service;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepo jobPostActivityRepo;

    public JobPostActivityService(JobPostActivityRepo jobPostActivityRepo) {
        this.jobPostActivityRepo = jobPostActivityRepo;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepo.save(jobPostActivity);
    }
}

