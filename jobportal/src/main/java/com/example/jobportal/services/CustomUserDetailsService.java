package com.example.jobportal.services;

import com.example.jobportal.entity.Users;
import com.example.jobportal.repository.UsersRepo;
import com.example.jobportal.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepo usersRepo;

    @Autowired
    public CustomUserDetailsService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Could not found user: "+username));
        return new CustomUserDetails(user);
    }
}
