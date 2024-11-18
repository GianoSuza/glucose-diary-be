package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADMIN FOOD")
public class AdminFood extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminFoodId;

    public AdminFood(String username, String password, Long adminFoodId) {
        super(username, password);
        this.adminFoodId = adminFoodId;
    }

    public AdminFood() {
    }

    public Long getAdminFoodId() {
        return adminFoodId;
    }

    public void setAdminFoodId(Long adminFoodId) {
        this.adminFoodId = adminFoodId;
    }
}
