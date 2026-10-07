package com.example.jthao_banking_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jthao_banking_backend.dto.request.CreateAccountRequest;
import com.example.jthao_banking_backend.service.AccountService;

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
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request){
        try {
            return ResponseEntity.ok(accountService.createAccount(request));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        
    }

    // Find all accounts for a customer
    // ** TODO: Refactor to User
    @GetMapping("/{id}/all")
    public ResponseEntity<?> getAllAccountsForCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findAllAccounts());
    }

    // Update account status

    // Delete/close account if balance = 0
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAccount(Long id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
    }

    // Get account by accountNumber

    // Update account and withdraw money

    // Update account and deposit money

    // GET all transactions for an account

    // 





}
