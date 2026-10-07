package com.example.jthao_banking_backend.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.example.jthao_banking_backend.dto.request.CreateAccountRequest;
import com.example.jthao_banking_backend.model.Account;
import com.example.jthao_banking_backend.model.User;
import com.example.jthao_banking_backend.repository.AccountRespository;
import com.example.jthao_banking_backend.repository.UserRepository;
import com.example.jthao_banking_backend.types.AccountStatus;

import jakarta.persistence.EntityNotFoundException;

@Service 
public class AccountService {
    private final AccountRespository accountRespository;
    private final UserRepository userRepository;

    public AccountService(AccountRespository accountRespository, UserRepository userRepository) {
        this.accountRespository = accountRespository;
        this.userRepository = userRepository;
    }

    public Account findById(Long id) {
        return accountRespository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }

    public List<Account> findByCustomerId(Long id) {
        return accountRespository.findByUserId(id);
    }

    public Account createAccount(CreateAccountRequest request) {
        String accountNumber = createAccountNumber();
        while (accountRespository.findByAccountNumber(accountNumber).isPresent()){
            accountNumber = createAccountNumber();
        }
        User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUserId()));
        return accountRespository.save(new Account(request.getAccountType(), request.getInitialDeposit(), accountNumber, user));

    }

    public String createAccountNumber(){
        long accountNum = ThreadLocalRandom.current().nextLong(10_000_000L, 100_000_000L);
        return String.valueOf(accountNum);
    }

    public List<Account> findAllAccounts() {
        return accountRespository.findAll(); // doesn't need exception handler
    }


    // GET account by account number
    public Account findByAccountNumber(String accountNum){
         return accountRespository.findByAccountNumber(accountNum)
         .orElseThrow(() -> new EntityNotFoundException("Account not found with account number: " + accountNum));
    }

    // Update account status

    // Delete - close account
    public Account closeAccount(String accountNumber) {
        Account account = findByAccountNumber(accountNumber);
        // check if balance is 0, if true => delete
        if (account.getAccountBalance().compareTo(BigDecimal.ZERO) == 0) {
            account.setAccountStatus(AccountStatus.CLOSED);
            accountRespository.saveAndFlush(account);
        } else {
            throw new IllegalStateException("Account cannot be closed if balance is not $0.");
        }
        return account;
    }

    // Get list of transactions for account

    // Post deposit or withdrawal

    // Post transfer funds between 2 accounts


}
