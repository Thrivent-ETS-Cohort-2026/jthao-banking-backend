package com.example.jthao_banking_backend.dto.request;


public class UpdateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    public UpdateUserRequest() {}


    // Getters
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
    
}
