package com.mycompany.application;

import javax.swing.JOptionPane;

public class Registration {

    public static Login registerUser() {
        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");

        String username;
        while (true) {
            username = JOptionPane.showInputDialog("Enter Username:");
            if (username.contains("_") && username.length() <= 5) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username must contain an underscore and be no more than 5 characters.");
            }
        }

        String password;
        while (true) {
            password = JOptionPane.showInputDialog("Enter Password:");
            if (password.matches("(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=]).{8,}")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password must contain a capital letter, number, special character, and be at least 8 characters long.");
            }
        }

        String cellNumber;
        while (true) {
            cellNumber = JOptionPane.showInputDialog("Enter Cell Phone Number (+27...):");
            if (cellNumber.matches("\\+27\\d{9}$")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Phone number must start with +27 and be valid.");
            }
        }

        Login login = new Login(username, password, cellNumber, firstName, lastName);
        JOptionPane.showMessageDialog(null, "User registered successfully.");
        return login;
    }
}
