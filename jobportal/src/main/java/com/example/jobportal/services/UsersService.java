package com.example.jobportal.services;

import com.example.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.entity.RecruiterProfile;
import com.example.jobportal.entity.Users;
import com.example.jobportal.repository.JobSeekerProfileRepo;
import com.example.jobportal.repository.RecruiterProfileRepo;
import com.example.jobportal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepo usersRepo;

    private final JobSeekerProfileRepo  jobSeekerProfileRepo;

    private final RecruiterProfileRepo recruiterProfileRepo;

    @Autowired
    public UsersService(UsersRepo usersRepo, JobSeekerProfileRepo jobSeekerProfileRepo, RecruiterProfileRepo recruiterProfileRepo) {
        this.usersRepo = usersRepo;
        this.jobSeekerProfileRepo = jobSeekerProfileRepo;
        this.recruiterProfileRepo = recruiterProfileRepo;
    }

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepo.save(users);
        int userTypeId = users.getUserTypeId().getUserTypeId();
        if(userTypeId==1){
            recruiterProfileRepo.save(new RecruiterProfile(savedUser));
        }else{
            jobSeekerProfileRepo.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }

    public Optional<Users> getUserByEmail(String email){
        return usersRepo.findByEmail(email);
    }
}
