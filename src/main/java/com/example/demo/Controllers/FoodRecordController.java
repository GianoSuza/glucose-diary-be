package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ResponseData;
import com.example.demo.Models.FoodRecord;
import com.example.demo.Services.FoodRecordService;

import jakarta.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("/food-record")
public class FoodRecordController {

    @Autowired
    private FoodRecordService foodRecordService;

    @PostMapping
    public ResponseEntity<ResponseData<FoodRecord>> createFoodRecord(@Valid @RequestBody FoodRecord foodRecord,
            Errors errors) {
        ResponseData<FoodRecord> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(foodRecordService.create(foodRecord));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<FoodRecord>> updateFoodRecord(@Valid @RequestBody FoodRecord newFoodRecord,
            @PathVariable Long id, Errors errors) {
        ResponseData<FoodRecord> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        try {
            responseData.setStatus(true);
            responseData.setPayload(foodRecordService.update(newFoodRecord, id));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessage().add("Food record not found");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<FoodRecord>> getFoodRecordById(@PathVariable Long id) {
        ResponseData<FoodRecord> responseData = new ResponseData<>();

        Optional<FoodRecord> foodRecord = Optional.ofNullable(foodRecordService.findById(id));
        if (foodRecord.isPresent()) {
            responseData.setStatus(true);
            responseData.setPayload(foodRecord.get());
            responseData.getMessage().add("Food record found successfully");
            return ResponseEntity.ok(responseData);
        } else {
            responseData.setStatus(false);
            responseData.getMessage().add("Food record not found");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<FoodRecord>>> getAllFoodRecords() {
        ResponseData<Iterable<FoodRecord>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(foodRecordService.findAll());
        responseData.getMessage().add("Food records retrieved successfully");
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteFoodRecordById(@PathVariable Long id) {
        ResponseData<Void> responseData = new ResponseData<>();

        try {
            foodRecordService.removeById(id);
            responseData.setStatus(true);
            responseData.getMessage().add("Food record deleted successfully");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessage().add("Food record not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseData<Void>> deleteAllFoodRecords() {
        ResponseData<Void> responseData = new ResponseData<>();
        foodRecordService.removeAll();
        responseData.setStatus(true);
        responseData.getMessage().add("All food records deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseData);
    }
}