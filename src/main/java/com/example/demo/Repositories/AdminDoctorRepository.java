package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.AdminDoctor;

@Repository
public interface AdminDoctorRepository extends JpaRepository<AdminDoctor, Long> {

}
