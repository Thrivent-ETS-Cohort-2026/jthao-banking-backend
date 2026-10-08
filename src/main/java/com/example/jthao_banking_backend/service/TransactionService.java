package com.example.jthao_banking_backend.service;

import com.example.jthao_banking_backend.validation.TransactionValidator;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.jthao_banking_backend.dto.request.TransactionRequest;
import com.example.jthao_banking_backend.model.Account;
import com.example.jthao_banking_backend.repository.AccountRespository;
import com.example.jthao_banking_backend.repository.UserRepository;
import com.example.jthao_banking_backend.types.AccountStatus;


@Service 
public class TransactionService {
    private final UserRepository userRepository;
    private final AccountRespository accountRespository;


    public TransactionService(UserRepository userRepository,
        AccountRespository accountRespository) {
        this.userRepository = userRepository;
        this.accountRespository = accountRespository;
    }

    // Post deposit
    public void deposit(String accountNumber, TransactionRequest request) {
        try {
            // check if amount is valid
            TransactionValidator.validateAmount(request.getAmount());
            // find the account by account number
            Account account = accountRespository.findByAccountNumber(accountNumber).orElseThrow(() -> new EntityNotFoundException("Account not found with account number: " + accountNumber));
            if (account.getStatus() != AccountStatus.OPEN) {
                throw new IllegalArgumentException("Cannot deposit into an account that is not open");
            }
            // deposit the amount into the account
            account.deposit(request.getAmount());
            accountRespository.saveAndFlush(account);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(ex.getMessage());
        }
         catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }
    
    // post withdrawal
    public void withdraw(String accountNumber, TransactionRequest request) {
        try {
            // check if amount is valid
            TransactionValidator.validateAmount(request.getAmount());
            // find account by account number
            Account account = accountRespository.findByAccountNumber(accountNumber).orElseThrow(() -> new EntityNotFoundException("Account not found with account number: " + accountNumber));
            if (account.getStatus() != AccountStatus.OPEN) {
                throw new IllegalArgumentException("Cannot withdraw from an account that is not open");
            }
            // deposit the amount into the account
            account.withdraw(request.getAmount());
            accountRespository.saveAndFlush(account);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(ex.getMessage());
        }
         catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }


    
    // transferService - Post transfer funds between 2 accounts

}
