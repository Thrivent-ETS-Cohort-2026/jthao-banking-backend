package com.example.jthao_banking_backend.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import com.example.jthao_banking_backend.dto.request.TransactionRequest;
import com.example.jthao_banking_backend.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController 
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Post deposit
    @PostMapping("/{accountNumber}/deposit")
    @Operation(summary = "Deposit money into an account", description = "Deposits a specified amount of money into the account identified by the account number.")
    public ResponseEntity<?> deposit(@PathVariable String accountNumber,
        @RequestBody TransactionRequest request) {
        try {
            transactionService.deposit(accountNumber, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // post withdrawal
    @PostMapping("/{accountNumber}/withdraw")
    @Operation(summary = "Withdraw money from an account", description = "Withdraws a specified amount of money from the account identified by the account number.")
    public ResponseEntity<?> withdraw(@PathVariable String accountNumber,
        @RequestBody TransactionRequest request) {
        try {
            transactionService.withdraw(accountNumber, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
