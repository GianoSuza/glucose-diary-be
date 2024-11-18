package com.example.demo.Models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "BOOK DOCTOR")
public class BookDoctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookDoctorId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Doctor doctor;

    @NotNull(message = "Date book is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;

    public BookDoctor(Long bookDoctorId, User user, Doctor doctor,
            @NotNull(message = "Date birth is required") Date bookingDate) {
        this.bookDoctorId = bookDoctorId;
        this.user = user;
        this.doctor = doctor;
        this.bookingDate = bookingDate;
    }

    public BookDoctor() {
    }

    public Long getBookDoctorId() {
        return bookDoctorId;
    }

    public void setBookDoctorId(Long bookDoctorId) {
        this.bookDoctorId = bookDoctorId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
