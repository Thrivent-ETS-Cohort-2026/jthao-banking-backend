package com.example.jthao_banking_backend.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jthao_banking_backend.dto.request.UpdateUserRequest;
import com.example.jthao_banking_backend.model.User;
import com.example.jthao_banking_backend.service.UserService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.RequestBody;
// import jakarta.persistence.EntityNotFoundException;

@RestController 
@RequestMapping("/customers")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // find all
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // create
    @PostMapping("/")
    public ResponseEntity<?> createUser(String fName, String lName, String email, String phoneNumber) {
        try {
        return ResponseEntity.ok(userService.createUser(fName, lName, email, phoneNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // find by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
        return ResponseEntity.ok(userService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // update contact info
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody  UpdateUserRequest request) {
        try {
            return ResponseEntity.ok(userService.updateUserDetails(id, request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // ** TODO: add validation for account balance before deleting
    // delete customer if account balance = 0
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            userService.deleteCustomerDetails(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // GET | List accounts for customer
    // ** TODO: refactor from Account to here




}
