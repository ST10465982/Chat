package com.mycompany.application;

import javax.swing.JOptionPane;

public class Application {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "--- Registration ---");
        Login login = Registration.registerUser();

        JOptionPane.showMessageDialog(null, "--- Login ---");
        boolean loggedIn = false;

        while (!loggedIn) {
            String loginUsername = JOptionPane.showInputDialog("Enter Username:");
            String loginPassword = JOptionPane.showInputDialog("Enter Password:");

            String status = login.returnLoginStatus(loginUsername, loginPassword);
            JOptionPane.showMessageDialog(null, status);

            if (status.contains("Welcome")) {
                loggedIn = true;

                // Start messaging
                MessageHandler msgHandler = new MessageHandler();
                msgHandler.startMessaging(loginUsername);
            }
        }
    }
}
