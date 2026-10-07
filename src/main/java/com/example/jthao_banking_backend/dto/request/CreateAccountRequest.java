package com.example.jthao_banking_backend.dto.request;

import java.math.BigDecimal;

import com.example.jthao_banking_backend.types.AccountType;

public class CreateAccountRequest {
    private AccountType accountType;
    // private String status;
    private BigDecimal initialDeposit;
    private Long userId;

    public AccountType getAccountType() {
        return accountType;
    }

    // public String getStatus() {
    //     return status;
    // }

    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    public Long getUserId() {
        return userId;
    }


}
