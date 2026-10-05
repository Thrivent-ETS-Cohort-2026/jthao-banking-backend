package com.example.jthao_banking_backend.model;

import java.math.BigDecimal;

import com.example.jthao_banking_backend.types.AccountType;

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

    @Column(name = "account_number")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

     @Column(name = "account_status")
    private String status;

     @Column(name = "initial_deposit")
    private BigDecimal initialDeposit;

     @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Account() {}

    protected Account(AccountType accountType, String status, BigDecimal deposit, User user) {
        // add validation here

        this.accountType = accountType;
        this.status = status;
        this.initialDeposit = deposit;
        this.user = user;
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

    public String getStatus() {
        return this.status;
    }

    public BigDecimal getInitialDeposit() {
        return this.initialDeposit;
    }

    public User getUser() {
        return this.user;
    }

    public BigDecimal getAccountBalance() {
        return this.accountBalance = accountBalance;
    }

    public void setAccountNumber(String newAccountNum) {
        this.accountNumber = newAccountNum;
    }

    public void setAccountStatus(String newStatus) {
        this.status = newStatus;
    }

    // deposit

    // withdrawal
   

}
