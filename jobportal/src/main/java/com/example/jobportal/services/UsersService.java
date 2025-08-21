package com.example.jobportal.services;

import com.example.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.entity.RecruiterProfile;
import com.example.jobportal.entity.Users;
import com.example.jobportal.repository.JobSeekerProfileRepo;
import com.example.jobportal.repository.RecruiterProfileRepo;
import com.example.jobportal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepo usersRepo;

    private final JobSeekerProfileRepo  jobSeekerProfileRepo;

    private final RecruiterProfileRepo recruiterProfileRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepo usersRepo, JobSeekerProfileRepo jobSeekerProfileRepo, RecruiterProfileRepo recruiterProfileRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.jobSeekerProfileRepo = jobSeekerProfileRepo;
        this.recruiterProfileRepo = recruiterProfileRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
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

    public Object getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users users = usersRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Could not found "+ "user"));
            int userId = users.getUserId();
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
                RecruiterProfile recruiterProfile = recruiterProfileRepo.findById(userId).orElse(new RecruiterProfile());
                return recruiterProfile;
            }else{
                JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepo.findById(userId).orElse(new JobSeekerProfile());
                return jobSeekerProfile;
            }
        }
        return null;
    }

    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users users = usersRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Could not found "+ "user"));
            return users;
        }
        return null;
    }

    public Users findByEmail(String currentUsername) {
        return usersRepo.findByEmail(currentUsername).orElseThrow(()-> new UsernameNotFoundException("Could not found "+ "user"));
    }
}
