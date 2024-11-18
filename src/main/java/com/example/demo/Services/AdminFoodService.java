package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.AdminFood;
import com.example.demo.Repositories.AdminFoodRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminFoodService {
    @Autowired
    private AdminFoodRepository adminFoodRepository;

    public AdminFood create(AdminFood adminFood) {
        return adminFoodRepository.save(adminFood);
    }

    public AdminFood findById(Long id) {
        return adminFoodRepository.findById(id).get();
    }

    public Iterable<AdminFood> findAll() {
        return adminFoodRepository.findAll();
    }

    public void removeById(Long id) {
        adminFoodRepository.deleteById(id);
    }

    public void removeAll() {
        adminFoodRepository.deleteAll();
    }
}
