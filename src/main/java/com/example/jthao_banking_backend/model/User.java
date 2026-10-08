package com.example.jthao_banking_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.jthao_banking_backend.validation.UserValidator;

import jakarta.persistence.Column;

@Entity
@Table(name = "users")
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;


    protected User() {}

    public User(String fName, String lName, String eMail, String phoneNumber) {
        UserValidator.validateUser(fName, lName, eMail, phoneNumber);

        this.firstName = fName;
        this.lastName = lName;
        this.email = eMail;
        this.phoneNumber = phoneNumber;
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

    public void setFirstName(String fName) {
        UserValidator.validateFirstName(fName);
        this.firstName = fName;
    }

    public void setLastName(String lName) {
        UserValidator.validateLastName(lName);
        this.lastName = lName;
    }

    public void setEmail(String eMail) {
        UserValidator.validateEmail(eMail);
        this.email = eMail;
    }

    public void setPhoneNumber(String phoneNum) {
        UserValidator.validatePhoneNumber(phoneNum);
        this.phoneNumber = phoneNum;
    }



}
