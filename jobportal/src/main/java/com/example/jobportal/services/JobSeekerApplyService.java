package com.example.jobportal.services;

import com.example.jobportal.entity.JobPostActivity;
import com.example.jobportal.entity.JobSeekerApply;
import com.example.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.repository.JobSeekerApplyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyService {
    private final JobSeekerApplyRepo jobSeekerApplyRepo;

    @Autowired
    public JobSeekerApplyService(JobSeekerApplyRepo jobSeekerApplyRepo) {
        this.jobSeekerApplyRepo = jobSeekerApplyRepo;
    }

    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId){
        return jobSeekerApplyRepo.findByUserId(userAccountId);
    }

    public List<JobSeekerApply> getJobCandidates(JobPostActivity job){
        return jobSeekerApplyRepo.findByJob(job);
    }


    public void addNew(JobSeekerApply jobSeekerApply) {
        jobSeekerApplyRepo.save(jobSeekerApply);
    }
}
