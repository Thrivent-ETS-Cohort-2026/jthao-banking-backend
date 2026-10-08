package com.example.jthao_banking_backend.dto.response;

import java.math.BigDecimal;

import com.example.jthao_banking_backend.types.AccountStatus;
import com.example.jthao_banking_backend.types.AccountType;


public class AccountResponse {
    private Long id;

    private String accountNumber;

    private AccountType accountType;

    private AccountStatus status;

    private BigDecimal initialDeposit;

    private BigDecimal accountBalance;


    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

}
