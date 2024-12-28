package com.example.demo.Config;

import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminDoctorRepository adminDoctorRepository;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminFoodRepository adminFoodRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws java.io.IOException {

        // Get the authenticated user from the SecurityContext
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        Object user = null;  // Declare the user object for different roles

        if (username.startsWith("doctor_")) {
            AdminDoctor adminDoctor = adminDoctorRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
            user = adminDoctor;  // Assign the correct object
        } else if (username.startsWith("food_")) {
            AdminFood adminFood = adminFoodRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
            user = adminFood;  // Assign the correct object
        } else if (username.startsWith("user_")) {
            AdminUser adminUser = adminUserRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
            user = adminUser;  // Assign the correct object
        } else {
            User defaultUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
            user = defaultUser;  // Assign the correct object
        }

        // Create the response object with user data
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        // Convert user data to JSON and send in response
        objectMapper.writeValue(response.getWriter(), user);
        response.getWriter().flush();
    }
}
