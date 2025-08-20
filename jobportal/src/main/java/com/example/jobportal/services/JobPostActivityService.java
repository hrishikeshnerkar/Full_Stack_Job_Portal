package com.example.jobportal.services;

import com.example.jobportal.entity.*;
import com.example.jobportal.repository.JobPostActivityRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepo jobPostActivityRepo;

    public JobPostActivityService(JobPostActivityRepo jobPostActivityRepo) {
        this.jobPostActivityRepo = jobPostActivityRepo;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepo.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){
        List<IRecruiterJobs> recruiterJobs = jobPostActivityRepo.getRecruiterJobs(recruiter);

        List<RecruiterJobsDto> recruiterJobsDtoList = new  ArrayList<>();

        for(IRecruiterJobs rec: recruiterJobs){
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title()
            ,loc, comp));
        }
        return recruiterJobsDtoList;
    }

    public JobPostActivity getOne(int id) {
        return jobPostActivityRepo.findById(id).orElseThrow(()-> new RuntimeException("Job not found"));
    }
}

