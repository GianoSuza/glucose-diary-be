package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.FoodRecord;
import com.example.demo.Services.FoodRecordService;

import java.util.Optional;

@RestController
@RequestMapping("/food-record")
public class FoodRecordController {

    @Autowired
    private FoodRecordService foodRecordService;

    @PostMapping
    public ResponseEntity<FoodRecord> createFoodRecord(@RequestBody FoodRecord foodRecord) {
        FoodRecord createdFoodRecord = foodRecordService.create(foodRecord);
        return new ResponseEntity<>(createdFoodRecord, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodRecord> updateFoodRecord(@RequestBody FoodRecord newFoodRecord, @PathVariable Long id) {
        try {
            FoodRecord updatedFoodRecord = foodRecordService.update(newFoodRecord, id);
            return new ResponseEntity<>(updatedFoodRecord, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodRecord> getFoodRecordById(@PathVariable Long id) {
        Optional<FoodRecord> foodRecord = Optional.ofNullable(foodRecordService.findById(id));
        return foodRecord.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Iterable<FoodRecord>> getAllFoodRecords() {
        Iterable<FoodRecord> foodRecords = foodRecordService.findAll();
        return new ResponseEntity<>(foodRecords, HttpStatus.OK);
    }

    @GetMapping("/weekData")
    public ResponseEntity<Iterable<FoodRecord>> getWeekData() {
        Iterable<FoodRecord> foodRecords = foodRecordService.getWeekData();
        return new ResponseEntity<>(foodRecords, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodRecordById(@PathVariable Long id) {
        try {
            foodRecordService.removeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllFoodRecords() {
        foodRecordService.removeAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
