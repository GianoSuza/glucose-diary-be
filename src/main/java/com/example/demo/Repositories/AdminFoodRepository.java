package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.AdminFood;

import java.util.Optional;

@Repository
public interface AdminFoodRepository extends JpaRepository<AdminFood, Long> {
    Optional<AdminFood> findByUsername(String username);
}
