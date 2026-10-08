package com.example.jthao_banking_backend.model;

import java.math.BigDecimal;

import com.example.jthao_banking_backend.types.AccountStatus;
import com.example.jthao_banking_backend.types.AccountType;
import com.example.jthao_banking_backend.validation.AccountValidator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
     @Column(name = "account_status", nullable = false)
    private AccountStatus status;

    @Column(name = "initial_deposit")
    private BigDecimal initialDeposit;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    protected Account() {}

    public Account(String accountType, BigDecimal deposit, String accountNumber, User user) {
        // add validation here
        AccountValidator.validateAccountCreation(accountType, deposit);

        this.accountType = AccountType.valueOf(accountType.toUpperCase());
        this.status = AccountStatus.OPEN;
        this.initialDeposit = deposit;
        this.user = user;
        this.accountBalance = deposit;
        this.accountNumber = accountNumber;

    }

    public Long getId() {
        return this.id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public BigDecimal getInitialDeposit() {
        return this.initialDeposit;
    }

    public User getUser() {
        return this.user;
    }

    public BigDecimal getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountNumber(String newAccountNum) {
        this.accountNumber = newAccountNum;
    }

    public void setAccountStatus(AccountStatus newStatus) {
        this.status = newStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // deposit
    public void deposit(BigDecimal amount) {
        this.accountBalance = this.accountBalance.add(amount);
    }

    // withdrawal
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(this.accountBalance) > 0) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal");
        }
        this.accountBalance = this.accountBalance.subtract(amount);
    }

}
