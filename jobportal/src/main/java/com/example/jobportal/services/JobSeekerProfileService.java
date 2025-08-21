package com.example.jobportal.services;

import com.example.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.entity.Users;
import com.example.jobportal.repository.JobSeekerProfileRepo;
import com.example.jobportal.repository.UsersRepo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepo  jobSeekerProfileRepo;

    private final UsersRepo usersRepo;

    public JobSeekerProfileService(JobSeekerProfileRepo jobSeekerProfileRepo, UsersRepo usersRepo) {
        this.jobSeekerProfileRepo = jobSeekerProfileRepo;
        this.usersRepo = usersRepo;
    }

    public Optional<JobSeekerProfile> getOne(Integer id){
        return jobSeekerProfileRepo.findById(id);
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepo.save(jobSeekerProfile);
    }

    public JobSeekerProfile getCurrentSeekerProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersRepo.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

            Optional<JobSeekerProfile> seekerProfile = getOne(users.getUserId());
            return seekerProfile.orElse(null);
        }else return null;
    }
}
