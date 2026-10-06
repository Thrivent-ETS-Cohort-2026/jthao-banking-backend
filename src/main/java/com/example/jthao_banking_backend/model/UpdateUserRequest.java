package com.example.jthao_banking_backend.model;


public class UpdateUserRequest {
    // private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    public UpdateUserRequest() {}

    // public Long getId() {
    //     return id;
    // }

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
    
    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

     public void setLastName(String lastName) {
        this.lastName = lastName;
    }

     public void setEmail(String email) {
        this.email = email;
    }
 
     public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
