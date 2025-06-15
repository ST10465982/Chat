package com.mycompany.application;

public class Login {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellphoneNumber;

    public Login(String username, String password, String cellphoneNumber, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        return password.matches("(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=]).{8,}");
    }

    public boolean checkCellPhoneNumber() {
        return cellphoneNumber.matches("^\\+27\\d{9}$");
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted.";
        } else if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted.";
        } else if (!checkCellPhoneNumber()) {
            return "Cell phone number is incorrectly formatted.";
        }
        return "User registered successfully.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
