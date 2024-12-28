package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.AdminDoctor;

import java.util.Optional;

@Repository
public interface AdminDoctorRepository extends JpaRepository<AdminDoctor, Long> {
    Optional<AdminDoctor> findByUsername(String username);
}
