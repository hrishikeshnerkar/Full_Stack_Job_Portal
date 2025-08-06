package com.example.jobportal.repository;


import com.example.jobportal.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterProfileRepo extends JpaRepository<RecruiterProfile, Integer> {
}
