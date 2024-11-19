package com.example.demo.Validators;

import com.example.demo.Repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        System.out.println("UserRepository: " + userRepository);
        // Skip validation if username is null or empty
        if (username == null || username.isEmpty()) {
            return true;
        }

        // Check if username already exists in the database
        return !userRepository.existsByUsername(username);
    }
}
