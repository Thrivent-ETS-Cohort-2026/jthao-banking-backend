package com.example.jthao_banking_backend.validation;

public class UserValidator {
    

    public static void validateUser(String fName, String lName, String eMail, String phoneNumber) {
        validateFirstName(fName);
        validateLastName(lName);
        validateEmail(eMail);
        validatePhoneNumber(phoneNumber);
    }

    public static void validateFirstName(String firstName) {
        if (firstName.isBlank() || firstName == null) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
    }

    public static void validateLastName(String lastName) {
        if (lastName.isBlank() || lastName == null) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
    }

    public static void validateEmail(String email) {
         if (email.isBlank() || email == null) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Please provide an email.");
        }
    }

    public static void validatePhoneNumber(String phoneNum) {
        if (phoneNum.isBlank() || phoneNum == null) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        for (int i = 0; i < phoneNum.length(); i++) {
            if (!Character.isDigit(phoneNum.charAt(i))) {
                throw new IllegalArgumentException("Please only enter numbers with no dashes, spaces, or paranthesis.");
            }
        }
    }


}
