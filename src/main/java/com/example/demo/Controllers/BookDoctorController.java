package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.BookDoctor;
import com.example.demo.Services.BookDoctorService;

import java.util.Optional;

@RestController
@RequestMapping("/book-doctor")
public class BookDoctorController {

    @Autowired
    private BookDoctorService bookDoctorService;

    @PostMapping
    public ResponseEntity<BookDoctor> createBookDoctor(@RequestBody BookDoctor bookDoctor) {
        BookDoctor createdBookDoctor = bookDoctorService.create(bookDoctor);
        return new ResponseEntity<>(createdBookDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDoctor> updateBookDoctor(@RequestBody BookDoctor newBookDoctor, @PathVariable Long id) {
        try {
            BookDoctor updatedBookDoctor = bookDoctorService.update(newBookDoctor, id);
            return new ResponseEntity<>(updatedBookDoctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDoctor> getBookDoctorById(@PathVariable Long id) {
        Optional<BookDoctor> bookDoctor = Optional.ofNullable(bookDoctorService.findById(id));
        return bookDoctor.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Iterable<BookDoctor>> getAllBookDoctors() {
        Iterable<BookDoctor> bookDoctors = bookDoctorService.findAll();
        return new ResponseEntity<>(bookDoctors, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookDoctorById(@PathVariable Long id) {
        try {
            bookDoctorService.removeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllBookDoctors() {
        bookDoctorService.removeAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
