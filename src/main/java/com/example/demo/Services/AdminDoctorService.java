package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.AdminDoctor;
import com.example.demo.Repositories.AdminDoctorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminDoctorService {
    @Autowired
    private AdminDoctorRepository adminDoctorRepository;

    public AdminDoctor create(AdminDoctor adminDoctor) {
        return adminDoctorRepository.save(adminDoctor);
    }

    public AdminDoctor findById(Long id) {
        return adminDoctorRepository.findById(id).get();
    }

    public Iterable<AdminDoctor> findAll() {
        return adminDoctorRepository.findAll();
    }

    public void removeById(Long id) {
        adminDoctorRepository.deleteById(id);
    }

    public void removeAll() {
        adminDoctorRepository.deleteAll();
    }
}
