package com.example.jthao_banking_backend.validation;

import java.math.BigDecimal;

import com.example.jthao_banking_backend.model.User;

public class AccountValidator {
    
    public static void valdidateAccountCreation(String accountType, BigDecimal deposit, User user) {
        if (accountType.isBlank() || accountType == null) {
            throw new IllegalArgumentException("Account type cannot be null or empty");
        }
        // if (status == null || status.isBlank()) {
        //     throw new IllegalArgumentException("Status cannot be null or empty");
        // }

        // not null
        if (deposit == null) {
            throw new IllegalArgumentException("Deposit amount cannot be null.");
        }

        // not negative
        if(deposit.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Deposit amount cannot be a negative number or less than 0.");
        }

        // has no more than 2 decimal places
        if ( deposit.stripTrailingZeros().scale() <= 2){
            throw new IllegalArgumentException("Deposit amount can only have two trailing numbers after the decimal. (0.00)");
        }

        // if user is blank or null IllegalArgumentException(""); 
        // if () {
        // }
    }
}
