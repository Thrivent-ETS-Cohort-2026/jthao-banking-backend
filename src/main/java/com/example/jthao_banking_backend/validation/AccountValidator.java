package com.example.jthao_banking_backend.validation;

import java.math.BigDecimal;
import java.util.Arrays;

import com.example.jthao_banking_backend.types.AccountType;
import com.example.jthao_banking_backend.types.AccountStatus;

public class AccountValidator {
    
    public static void validateAccountCreation(String accountType, BigDecimal deposit) {

        isValidAccountType(accountType);
        validateDeposit(deposit);
    }

    public static void validateDeposit(BigDecimal deposit) {
        // not null
        if (deposit == null) {
            throw new IllegalArgumentException("Deposit amount cannot be null.");
        }

        // not negative
        if(deposit.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Deposit amount cannot be a negative number or less than 0.");
        }

        // has no more than 2 decimal places
        if ( deposit.scale() > 2){
            throw new IllegalArgumentException("Deposit amount can not have more than 2 decimal places.");
        }
    }

    public static void isValidAccountType(String accountType) {
        if (accountType == null) {
            throw new IllegalArgumentException("Account type is required");
        }
        try {
            AccountType.valueOf(accountType.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid account type: " + accountType);
        }
    }

    public static AccountStatus isValidAccountStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }
        try {
            AccountStatus newStatus = AccountStatus.valueOf(status.toUpperCase());
            System.out.println("Enum value found: " + newStatus);
            return newStatus;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid account status: " + status);
        }
    }

    

}
