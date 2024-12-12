package com.example.demo.Models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;

@MappedSuperclass
public class Account implements Serializable {

    @NotEmpty(message = "Username is required")
    @Column(length = 50, unique = true)
    private String username;

    @NotEmpty(message = "Password is required")
    @Column(length = 100)
    private String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
