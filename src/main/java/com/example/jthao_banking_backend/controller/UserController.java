package com.example.jthao_banking_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jthao_banking_backend.service.UserService;

@RestController 
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(String fName, String lName, String email, String phoneNumber) {
        return ResponseEntity.ok(userService.createUser(fName, lName, email, phoneNumber));
    }

    // find by id

    // update contact info

    // delete customer if account balance = 0
    
}
