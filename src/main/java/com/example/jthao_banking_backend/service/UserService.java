package com.example.jthao_banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jthao_banking_backend.model.UpdateUserRequest;
import com.example.jthao_banking_backend.model.User;
import com.example.jthao_banking_backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

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

    public User findById(Long id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public User updateUserDetails(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            user.setFirstName(request.getFirstName());
        }
        System.out.println("Existing last name: " + user.getLastName());
        System.out.println("Request last name: " + request.getLastName());
        if (request.getLastName() != null && !request.getLastName().isEmpty()) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        
        return userRepository.saveAndFlush(user);
    }


}
