package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADMIN USER")
public class AdminUser extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminUserId;

    public AdminUser(String username, String password, Long adminUserId) {
        super(username, password);
        this.adminUserId = adminUserId;
    }

    public AdminUser() {
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }
}
