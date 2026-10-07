package com.example.jthao_banking_backend.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jthao_banking_backend.dto.request.UpdateUserRequest;
import com.example.jthao_banking_backend.model.Account;
import com.example.jthao_banking_backend.model.User;
import com.example.jthao_banking_backend.dto.response.UserIdResponse;
import com.example.jthao_banking_backend.repository.AccountRespository;
import com.example.jthao_banking_backend.repository.UserRepository;
import com.example.jthao_banking_backend.types.AccountStatus;

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
            return userRepository.saveAndFlush(new User(fName, lName, email, phoneNumber));
        }

    public User findById(Long id) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return user;
    }

    public UserIdResponse getAllAccountsForUser(User user) {
        ArrayList<Account> accountList = new ArrayList<>(accountRespository.findByUserId(user.getId()));

        UserIdResponse response = new UserIdResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAccounts(accountList);

        return response;
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
        for (Account account : accountList) {
            if (account.getStatus().equals(AccountStatus.SUSPENDED) || account.getStatus().equals(AccountStatus.OPEN)) {
                throw new IllegalStateException("Customer cannot be deleted while an account is still active.");
            } else {
                account.setUser(null);
                accountRespository.saveAndFlush(account);
            }
        }

        userRepository.deleteById(id);
    }


}
