package com.example.jthao_banking_backend.validation;

public class AccountValidator {
    
    public static void valdidateAccountCreation(String accountType, String status, Double deposit, Long userId) {
        if (accountType.isBlank() || accountType == null) {
            throw new IllegalArgumentException("Account type cannot be null or empty");
        }
    }
}
