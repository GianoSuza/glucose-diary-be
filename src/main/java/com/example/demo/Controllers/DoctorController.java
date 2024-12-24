package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ResponseData;
import com.example.demo.Models.Doctor;
import com.example.demo.Services.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ResponseData<Doctor>> create(@Valid @RequestBody Doctor doctor, Errors errors) {
        ResponseData<Doctor> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(doctorService.create(doctor));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Doctor>> update(@Valid @RequestBody Doctor doctor, @PathVariable Long id,
            Errors errors) {
        ResponseData<Doctor> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(doctorService.update(doctor, id));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Doctor>> findById(@PathVariable("id") Long id) {
        ResponseData<Doctor> responseData = new ResponseData<>();

        Doctor doctor = doctorService.findById(id); // Call the service to find the user
        if (doctor == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Doctor not found"); // Add a custom message
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessage().add("Doctor found successfully"); // Optional success message
        responseData.setPayload(doctor);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Doctor>>> findAll() {
        ResponseData<Iterable<Doctor>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(doctorService.findAll());
        responseData.getMessage().add("Doctors retrieved successfully");
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> removeById(@PathVariable("id") Long id) {
        ResponseData<Void> responseData = new ResponseData<>();

        try {
            doctorService.removeById(id);
            responseData.setStatus(true);
            responseData.getMessage().add("Doctor removed successfully");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessage().add("Doctor not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseData<Void>> removeAll() {
        ResponseData<Void> responseData = new ResponseData<>();
        doctorService.removeAll();
        responseData.setStatus(true);
        responseData.getMessage().add("All doctors removed successfully");
        return ResponseEntity.ok(responseData);
    }
}
