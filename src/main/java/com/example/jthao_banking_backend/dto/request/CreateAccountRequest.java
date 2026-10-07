package com.example.jthao_banking_backend.dto.request;

import java.math.BigDecimal;

import com.example.jthao_banking_backend.types.AccountType;

public class CreateAccountRequest {
    private AccountType accountType;
    private BigDecimal initialDeposit;
    private Long userId;

    
    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    public Long getUserId() {
        return userId;
    }


}
