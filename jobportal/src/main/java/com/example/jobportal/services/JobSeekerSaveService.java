package com.example.jobportal.services;

import com.example.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.entity.JobSeekerSave;
import com.example.jobportal.repository.JobSeekerSaveRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerSaveService {

    private final JobSeekerSaveRepo jobSeekerSaveRepo;

    public JobSeekerSaveService(JobSeekerSaveRepo jobSeekerSaveRepo) {
        this.jobSeekerSaveRepo = jobSeekerSaveRepo;
    }

    public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId){
        return jobSeekerSaveRepo.findByUserId(userAccountId);
    }

    public
}
