package com.example.demo.Models;

import java.io.Serializable;
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

    @NotEmpty(message = "Practice day is required")
    private String practiceDay;

    private String images;

    public Doctor(Long doctorId, String name, String email, String speciality, String practiceDay, String images) {
        this.doctorId = doctorId;
        this.name = name;
        this.email = email;
        this.speciality = speciality;
        this.practiceDay = practiceDay;
        this.images = images;
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

    public String getPracticeDay() {
        return practiceDay;
    }

    public void setPracticeDay(String practiceDay) {
        this.practiceDay = practiceDay;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
