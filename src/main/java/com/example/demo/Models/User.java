package com.example.demo.Models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "USER")
public class User extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "Email is required")
    @Column(length = 100)
    private String email;

    @NotNull(message = "Date birth is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBirth;

    @NotEmpty(message = "Gender is required")
    @Column(length = 100)
    private String gender;

    public User() {
    }

    public User(Long userId, String email, Date dateBirth, String gender) {
        this.userId = userId;
        this.email = email;
        this.dateBirth = dateBirth;
        this.gender = gender;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
