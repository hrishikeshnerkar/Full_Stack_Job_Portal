package com.example.jobportal.repository;

import com.example.jobportal.entity.Users;
import com.example.jobportal.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTypeRepo extends JpaRepository<UsersType, Integer> {
}
