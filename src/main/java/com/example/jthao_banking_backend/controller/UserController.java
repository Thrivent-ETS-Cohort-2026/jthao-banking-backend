package com.example.jthao_banking_backend.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jthao_banking_backend.dto.request.LoginRequest;
import com.example.jthao_banking_backend.dto.request.CreateUserRequest;
import com.example.jthao_banking_backend.dto.request.UpdateUserRequest;
import com.example.jthao_banking_backend.model.User;
import com.example.jthao_banking_backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;


@CrossOrigin(origins = "http://localhost:3000")
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
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided first name, last name, email, and phone number.")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
        return ResponseEntity.ok(userService.createUser(request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // find by id
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves the details of a user using their ID.")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // update contact info
    @PatchMapping("/{id}")
    @Operation(summary = "Update user contact information", description = "Updates the contact information of an existing user identified by their ID.")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody  UpdateUserRequest request) {
        try {
            return ResponseEntity.ok(userService.updateUserDetails(id, request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // delete customer if account balance = 0
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Deletes an existing user identified by their ID if the account balance is zero.")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            userService.deleteCustomerDetails(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    // GET | List accounts for customer
    @GetMapping("/{id}/accounts")
    @Operation(summary = "List all accounts for a user", description = "Retrieves all accounts associated with the user identified by their ID.")
    public ResponseEntity<?> findAllAccountsForId(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(userService.getAllAccountsForUser(user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Check if the credentials are valid, then returns the user details", description = "Checks if the credentials coming in are valid. If they are, returns the user details based on their email address and password.")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(userService.checkCredentials(request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
