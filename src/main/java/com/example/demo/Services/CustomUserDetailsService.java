package com.example.demo.Services;

import com.example.demo.Models.AdminFood;
import com.example.demo.Models.AdminDoctor;
import com.example.demo.Models.AdminUser;
import com.example.demo.Models.User;
import com.example.demo.Repositories.AdminFoodRepository;
import com.example.demo.Repositories.AdminDoctorRepository;
import com.example.demo.Repositories.AdminUserRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminFoodRepository adminFoodRepository;

    @Autowired
    private AdminDoctorRepository adminDoctorRepository;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = resolveUserRole(username);

        if ("FOOD".equals(role)) {
            // Fetch user from admin_food table
            AdminFood adminFood = adminFoodRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in admin_food table with username: " + username));

            // Build UserDetails with the FOOD role
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(adminFood.getUsername());
            builder.password(adminFood.getPassword()); // Ensure password is encoded
            builder.roles("FOOD"); // Assign the FOOD role
            return builder.build();

        } else if ("DOCTOR".equals(role)) {
            // Fetch user from admin_doctor table
            AdminDoctor adminDoctor = adminDoctorRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in admin_doctor table with username: " + username));

            // Build UserDetails with the DOCTOR role
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(adminDoctor.getUsername());
            builder.password(adminDoctor.getPassword()); // Ensure password is encoded
            builder.roles("DOCTOR"); // Assign the DOCTOR role
            return builder.build();
        } else if ("USER".equals(role)) {
            // Fetch user from admin_doctor table
            AdminUser adminUser = adminUserRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in admin_user table with username: " + username));

            // Build UserDetails with the DOCTOR role
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(adminUser.getUsername());
            builder.password(adminUser.getPassword()); // Ensure password is encoded
            builder.roles("USER"); // Assign the DOCTOR role
            return builder.build();
        } else if ("DEFAULT".equals(role)) {
            // Default to user table
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in user table with username: " + username));

            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
            builder.password(user.getPassword()); // Encoded password
            builder.roles("DEFAULT"); // Default role
            return builder.build();
        } else {
            throw new UsernameNotFoundException("Unknown role for user with username: " + username);
        }
    }

    private String resolveUserRole(String username) {
        if (username.startsWith("food_")) {
            return "FOOD";
        } else if (username.startsWith("doctor_")) {
            return "DOCTOR";
        } else if (username.startsWith("user_")) {
            return "USER";
        } else {
            return "DEFAULT";
        }
    }
}
