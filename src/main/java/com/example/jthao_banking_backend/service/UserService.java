package com.example.jthao_banking_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jthao_banking_backend.dto.request.UpdateUserRequest;
import com.example.jthao_banking_backend.model.Account;
import com.example.jthao_banking_backend.model.User;
import com.example.jthao_banking_backend.repository.AccountRespository;
import com.example.jthao_banking_backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service 
public class UserService {
    private final UserRepository userRepository;
    private final AccountRespository accountRespository;

    public UserService(UserRepository userRepository,
        AccountRespository accountRespository) {
        this.userRepository = userRepository;
        this.accountRespository = accountRespository;
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
        User user = findById(id);

        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            user.setFirstName(request.getFirstName());
        }
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

    public void deleteCustomerDetails(Long id) {
        User user = findById(id);
        // use id to find accounts and check balance
        ArrayList<Account> accountList = new ArrayList<>(accountRespository.findByUserId(id));
        System.out.println("=================");
        System.out.println(accountList);
        System.out.println("=================");
        // Getting a 500 error


        // Need to check if their account balance is $0.
        // if it is, then you can delete.
        // otherwise, don't delete.
        // check for both Checking and Savings accounts, or all accounts
        userRepository.delete(user);
    }


}
