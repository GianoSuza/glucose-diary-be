package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ResponseData;
import com.example.demo.Models.User;
import com.example.demo.Services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseData<User>> create(@Valid @RequestBody User user, Errors errors) {
        ResponseData<User> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(userService.create(user));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<User>> update(@Valid @RequestBody User user, @PathVariable Long id,
            Errors errors) {
        ResponseData<User> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(userService.update(user, id));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<User>> findById(@PathVariable("id") Long id) {
        ResponseData<User> responseData = new ResponseData<>();

        User user = userService.findById(id); // Call the service to find the user
        if (user == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("User not found"); // Add a custom message
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessage().add("User found successfully"); // Optional success message
        responseData.setPayload(user);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<User>>> findAll() {
        ResponseData<Iterable<User>> responseData = new ResponseData<>();
        Iterable<User> users = userService.findAll();

        if (!users.iterator().hasNext()) {
            responseData.setStatus(false);
            responseData.getMessage().add("No users found");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessage().add("Users retrieved successfully");
        responseData.setPayload(users);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> removeById(@PathVariable("id") Long id) {
        ResponseData<Void> responseData = new ResponseData<>();

        if (userService.findById(id) == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("User not found");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        userService.removeById(id);
        responseData.setStatus(true);
        responseData.getMessage().add("User deleted successfully");
        responseData.setPayload(null);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping
    public ResponseEntity<ResponseData<Void>> removeAll() {
        ResponseData<Void> responseData = new ResponseData<>();

        userService.removeAll();
        responseData.setStatus(true);
        responseData.getMessage().add("All user deleted successfully");
        responseData.setPayload(null);
        return ResponseEntity.ok(responseData);
    }
}
