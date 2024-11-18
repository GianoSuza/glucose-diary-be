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
    public Doctor findById(@PathVariable("id") Long id) {
        return doctorService.findById(id);
    }

    @GetMapping
    public Iterable<Doctor> findAll() {
        return doctorService.findAll();
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") Long id) {
        doctorService.removeById(id);
    }

    @DeleteMapping
    public void removeAll() {
        doctorService.removeAll();
    }
}
