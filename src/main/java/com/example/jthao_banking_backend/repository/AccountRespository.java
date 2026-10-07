package com.example.jthao_banking_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jthao_banking_backend.model.Account;

public interface AccountRespository extends JpaRepository<Account, Long>{
    
    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByUserId(Long id);
}
