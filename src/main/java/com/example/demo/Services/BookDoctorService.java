package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.BookDoctor;
import com.example.demo.Repositories.BookDoctorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookDoctorService {
    @Autowired
    private BookDoctorRepository bookDoctorRepository;

    public BookDoctor create(BookDoctor bookDoctor) {
        return bookDoctorRepository.save(bookDoctor);
    }

    public BookDoctor update(BookDoctor newBookDoctor, Long id) {
        return bookDoctorRepository.findById(id)
                .map(bookDoctor -> {
                    bookDoctor.setUser(newBookDoctor.getUser());
                    bookDoctor.setDoctor(newBookDoctor.getDoctor());
                    bookDoctor.setBookingDate(newBookDoctor.getBookingDate());
                    return bookDoctorRepository.save(bookDoctor);
                })
                .orElseGet(() -> {
                    return bookDoctorRepository.save(newBookDoctor);
                });
    }

    public BookDoctor findById(Long id) {
        return bookDoctorRepository.findById(id).get();
    }

    public Iterable<BookDoctor> findAll() {
        return bookDoctorRepository.findAll();
    }

    public void removeById(Long id) {
        bookDoctorRepository.deleteById(id);
    }

    public void removeAll() {
        bookDoctorRepository.deleteAll();
    }
}
