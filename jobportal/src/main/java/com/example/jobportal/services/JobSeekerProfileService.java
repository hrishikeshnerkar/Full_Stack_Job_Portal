package com.example.jobportal.services;

import com.example.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.repository.JobSeekerProfileRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepo  jobSeekerProfileRepo;

    public JobSeekerProfileService(JobSeekerProfileRepo jobSeekerProfileRepo) {
        this.jobSeekerProfileRepo = jobSeekerProfileRepo;
    }

    public Optional<JobSeekerProfile> getOne(Integer id){
        return jobSeekerProfileRepo.findById(id);
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepo.save(jobSeekerProfile);
    }
}
