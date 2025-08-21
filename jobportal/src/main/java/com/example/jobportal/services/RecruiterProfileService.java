package com.example.jobportal.services;

import com.example.jobportal.entity.RecruiterProfile;
import com.example.jobportal.entity.Users;
import com.example.jobportal.repository.RecruiterProfileRepo;
import com.example.jobportal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {
    private final RecruiterProfileRepo recruiterProfileRepo;

    private  final UsersRepo  usersRepo;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepo recruiterProfileRepo, UsersRepo usersRepo) {
        this.recruiterProfileRepo = recruiterProfileRepo;
        this.usersRepo = usersRepo;
    }

    public Optional<RecruiterProfile> getId(Integer id){
        return recruiterProfileRepo.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepo.save(recruiterProfile);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersRepo.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Optional<RecruiterProfile> recruiterProfile = getOne(users.getUserId());
            return recruiterProfile.orElse(null);
        }else return null;
    }

    private Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterProfileRepo.findById(id);
    }
}
