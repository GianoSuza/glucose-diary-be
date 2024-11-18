package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADMIN DOCTOR")
public class AdminDoctor extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminDoctorId;

    public AdminDoctor(String username, String password, Long adminDoctorId) {
        super(username, password);
        this.adminDoctorId = adminDoctorId;
    }

    public AdminDoctor() {
    }

    public Long getAdminDoctorId() {
        return adminDoctorId;
    }

    public void setAdminDoctorId(Long adminDoctorId) {
        this.adminDoctorId = adminDoctorId;
    }
}
