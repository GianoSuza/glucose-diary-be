package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ResponseData;
import com.example.demo.Models.BookDoctor;
import com.example.demo.Services.BookDoctorService;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/book-doctor")
public class BookDoctorController {

    @Autowired
    private BookDoctorService bookDoctorService;

    @PostMapping
    public ResponseEntity<ResponseData<BookDoctor>> createBookDoctor(@Valid @RequestBody BookDoctor bookDoctor,
            Errors errors) {
        ResponseData<BookDoctor> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(bookDoctorService.create(bookDoctor));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<BookDoctor>> updateBookDoctor(@Valid @RequestBody BookDoctor newBookDoctor,
            @PathVariable Long id, Errors errors) {
        ResponseData<BookDoctor> responseData = new ResponseData<>();

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
            responseData.setPayload(bookDoctorService.update(newBookDoctor, id));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessage().add("Book doctor record not found");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<BookDoctor>> getBookDoctorById(@PathVariable Long id) {
        ResponseData<BookDoctor> responseData = new ResponseData<>();

        Optional<BookDoctor> bookDoctor = Optional.ofNullable(bookDoctorService.findById(id));
        if (bookDoctor.isPresent()) {
            responseData.setStatus(true);
            responseData.setPayload(bookDoctor.get());
            responseData.getMessage().add("Book doctor record found successfully");
            return ResponseEntity.ok(responseData);
        } else {
            responseData.setStatus(false);
            responseData.getMessage().add("Book doctor record not found");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<BookDoctor>>> getAllBookDoctors() {
        ResponseData<Iterable<BookDoctor>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(bookDoctorService.findAll());
        responseData.getMessage().add("Book doctor records retrieved successfully");
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteBookDoctorById(@PathVariable Long id) {
        ResponseData<Void> responseData = new ResponseData<>();

        try {
            bookDoctorService.removeById(id);
            responseData.setStatus(true);
            responseData.getMessage().add("Book doctor record deleted successfully");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessage().add("Book doctor record not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseData<Void>> deleteAllBookDoctors() {
        ResponseData<Void> responseData = new ResponseData<>();
        bookDoctorService.removeAll();
        responseData.setStatus(true);
        responseData.getMessage().add("All book doctor records deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseData);
    }
}
