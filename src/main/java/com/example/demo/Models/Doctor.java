package com.example.demo.Models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "DOCTOR")
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @NotEmpty(message = "Doctor name is required")
    private String name;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Speciality is required")
    private String speciality;

    @ElementCollection
    private List<String> practiceDay;

    public Doctor(Long doctorId, @NotEmpty(message = "Doctor name is required") String name,
            @NotEmpty(message = "Email is required") String email,
            @NotEmpty(message = "Speciality is required") String speciality, List<String> practiceDay) {
        this.doctorId = doctorId;
        this.name = name;
        this.email = email;
        this.speciality = speciality;
        this.practiceDay = practiceDay;
    }

    public Doctor() {
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<String> getPracticeDay() {
        return practiceDay;
    }

    public void setPracticeDay(List<String> practiceDay) {
        this.practiceDay = practiceDay;
    }
}
