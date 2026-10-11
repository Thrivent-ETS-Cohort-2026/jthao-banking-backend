package com.example.jthao_banking_backend.dto.request;

public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

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

    public String getPassword() {
        return password;
    }

}
