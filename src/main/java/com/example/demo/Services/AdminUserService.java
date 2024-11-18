package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.AdminUser;
import com.example.demo.Repositories.AdminUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminUserService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    public AdminUser create(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }

    public AdminUser findById(Long id) {
        return adminUserRepository.findById(id).get();
    }

    public Iterable<AdminUser> findAll() {
        return adminUserRepository.findAll();
    }

    public void removeById(Long id) {
        adminUserRepository.deleteById(id);
    }

    public void removeAll() {
        adminUserRepository.deleteAll();
    }
}
