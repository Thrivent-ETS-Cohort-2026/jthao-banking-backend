package com.example.jthao_banking_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jthao_banking_backend.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
}
