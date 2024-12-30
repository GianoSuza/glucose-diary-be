package com.example.demo.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.User;
import com.example.demo.Repositories.BookDoctorRepository;
import com.example.demo.Repositories.FoodRecordRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRecordRepository foodRecordRepository;

    @Autowired
    private BookDoctorRepository bookDoctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    user.setDateBirth(newUser.getDateBirth());
                    user.setGender(newUser.getGender());
                    user.setImages(newUser.getImages());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    return userRepository.save(newUser);
                });
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        return user.get();
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void removeById(Long id) {
        foodRecordRepository.deleteAllByUserId(id);
        bookDoctorRepository.deleteAllByUserId(id);

        userRepository.deleteById(id);
    }

    public void removeAll() {
        userRepository.deleteAll();
    }

    public Object login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        } else {
            return "Invalid username or password";
        }
    }
}