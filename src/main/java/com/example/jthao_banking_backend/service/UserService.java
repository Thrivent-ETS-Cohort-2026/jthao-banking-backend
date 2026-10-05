package com.example.jthao_banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jthao_banking_backend.model.User;
import com.example.jthao_banking_backend.repository.UserRepository;

@Service 
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(String fName, String lName, 
        String email, String phoneNumber) {
            return userRepository.save(new User(fName, lName, email, phoneNumber));
        }

    


}
