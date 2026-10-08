package com.example.jthao_banking_backend.dto.response;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<AccountResponse> accounts;


    public UserResponse(Long id, String firstName, String lastName, String email, String phoneNumber, List<AccountResponse> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accounts = (accounts != null) ? new ArrayList<>(accounts) : new ArrayList<>();
    }
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<AccountResponse> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

}
