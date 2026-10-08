package com.example.jthao_banking_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jthao_banking_backend.dto.request.CreateAccountRequest;
import com.example.jthao_banking_backend.dto.request.UpdateStatusRequest;
import com.example.jthao_banking_backend.service.AccountService;
import com.example.jthao_banking_backend.types.AccountStatus;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;

@RestController 
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    // Create account
    @PostMapping("/")
    @Operation(summary = "Create a new account", description = "Creates a new account for a user. The default is a CHECKING account. Another option is a SAVINGS account. The account type must be specified in the request along with the customer's first name, last name, email, and phone number.")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request){
        try {
            return ResponseEntity.ok(accountService.createAccount(request));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        
    }

    // Update account status
    @PatchMapping("/{accountNumber}")
    @Operation(summary = "Update account status", description = "Updates the status of an existing account.")
    public ResponseEntity<?> updateAccountStatus(@PathVariable String accountNumber, @RequestBody UpdateStatusRequest request) {
        try {
            return ResponseEntity.ok(accountService.updateStatus(accountNumber, request.getStatus()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    // Delete/close account if balance = 0
    @PatchMapping("/{accountNumber}/deactivate")
    @Operation(summary = "Deactivate an account", description = "Deactivates an existing account if the balance is zero. by changing the account's status to CLOSED.")
    public ResponseEntity<?> deactivateAccount(@PathVariable String accountNumber) {
        try {
            return ResponseEntity.ok(accountService.closeAccount(accountNumber));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalStateException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
    }

    // Get account by accountNumber
    @GetMapping("/{accountNumber}")
    @Operation(summary = "Get account by account number", description = "Retrieves the details of an account using its account number.")
    public ResponseEntity<?> getAccountByAccountNumber(@PathVariable String accountNumber) {
        try {
            return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // Update account and withdraw money

    // Update account and deposit money

    // GET all transactions for an account

    // 





}
