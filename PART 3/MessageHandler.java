package com.mycompany.application;

import javax.swing.*;
import java.util.UUID;

public class MessageHandler {

    // Parallel arrays for Sent messages
    private String[] sentIds = new String[100];
    private String[] sentRecipients = new String[100];
    private String[] sentContents = new String[100];
    private String[] sentFlags = new String[100];
    private int[] sentHashes = new int[100];
    private int sentCount = 0;

    // Parallel arrays for Stored messages
    private String[] storedIds = new String[100];
    private String[] storedRecipients = new String[100];
    private String[] storedContents = new String[100];
    private String[] storedFlags = new String[100];
    private int[] storedHashes = new int[100];
    private int storedCount = 0;

    // Parallel arrays for Disregarded messages
    private String[] disregardedIds = new String[100];
    private String[] disregardedRecipients = new String[100];
    private String[] disregardedContents = new String[100];
    private String[] disregardedFlags = new String[100];
    private int[] disregardedHashes = new int[100];
    private int disregardedCount = 0;

    public void startMessaging(String username) {
        boolean exit = false;

        do {
            String[] options = {
                    "Send Message", "Display Sent Messages",
                    "Display Longest Message", "Search by ID",
                    "Search by Recipient", "Delete by Hash",
                    "Display Full Report", "Quit"
            };
            int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Messaging Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    sendMessage();
                    break;
                case 1:
                    displaySentMessages();
                    break;
                case 2:
                    displayLongestMessage();
                    break;
                case 3:
                    searchByID();
                    break;
                case 4:
                    searchByRecipient();
                    break;
                case 5:
                    deleteByHash();
                    break;
                case 6:
                    displayFullReport();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
            }

        } while (!exit);

        JOptionPane.showMessageDialog(null, "You have logged out. Goodbye!");
    }

    private void sendMessage() {
        String recipient = JOptionPane.showInputDialog("Enter recipient number:");
        String content = JOptionPane.showInputDialog("Enter your message:");
        String flag = assignFlag(content);
        String id = UUID.randomUUID().toString().substring(0, 8);
        int hash = content.hashCode();

        switch (flag) {
            case "Sent":
                if (sentCount < sentIds.length) {
                    sentIds[sentCount] = id;
                    sentRecipients[sentCount] = recipient;
                    sentContents[sentCount] = content;
                    sentFlags[sentCount] = flag;
                    sentHashes[sentCount] = hash;
                    sentCount++;
                } else {
                    JOptionPane.showMessageDialog(null, "Sent messages storage full!");
                }
                break;
            case "Stored":
                if (storedCount < storedIds.length) {
                    storedIds[storedCount] = id;
                    storedRecipients[storedCount] = recipient;
                    storedContents[storedCount] = content;
                    storedFlags[storedCount] = flag;
                    storedHashes[storedCount] = hash;
                    storedCount++;
                } else {
                    JOptionPane.showMessageDialog(null, "Stored messages storage full!");
                }
                break;
            case "Disregard":
                if (disregardedCount < disregardedIds.length) {
                    disregardedIds[disregardedCount] = id;
                    disregardedRecipients[disregardedCount] = recipient;
                    disregardedContents[disregardedCount] = content;
                    disregardedFlags[disregardedCount] = flag;
                    disregardedHashes[disregardedCount] = hash;
                    disregardedCount++;
                } else {
                    JOptionPane.showMessageDialog(null, "Disregarded messages storage full!");
                }
                break;
        }

        JOptionPane.showMessageDialog(null, "Message sent with ID: " + id + " | Flag: " + flag);
    }

    private String assignFlag(String message) {
        if (message.toLowerCase().contains("yohoo") || message.length() < 15) {
            return "Disregard";
        } else if (message.length() > 50) {
            return "Stored";
        } else {
            return "Sent";
        }
    }

    private void displaySentMessages() {
        if (sentCount == 0) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }

        StringBuilder output = new StringBuilder("Sent Messages:\n");
        for (int i = 0; i < sentCount; i++) {
            output.append("To: ").append(sentRecipients[i]).append("\nMessage: ").append(sentContents[i]).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }

    private void displayLongestMessage() {
        if (sentCount == 0) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }

        int longestIndex = 0;
        int maxLength = sentContents[0].length();

        for (int i = 1; i < sentCount; i++) {
            if (sentContents[i].length() > maxLength) {
                maxLength = sentContents[i].length();
                longestIndex = i;
            }
        }

        JOptionPane.showMessageDialog(null, "Longest Message:\n" + sentContents[longestIndex]);
    }

    private void searchByID() {
        String id = JOptionPane.showInputDialog("Enter message ID:");
        // Search sent messages
        for (int i = 0; i < sentCount; i++) {
            if (sentIds[i].equals(id)) {
                JOptionPane.showMessageDialog(null,
                        "Message Found:\nTo: " + sentRecipients[i] + "\nMessage: " + sentContents[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found in Sent Messages.");
    }

    private void searchByRecipient() {
        String recipient = JOptionPane.showInputDialog("Enter recipient number:");
        StringBuilder result = new StringBuilder();

        // Search sent messages
        for (int i = 0; i < sentCount; i++) {
            if (sentRecipients[i].equals(recipient)) {
                result.append(sentContents[i]).append("\n");
            }
        }

        // Search stored messages
        for (int i = 0; i < storedCount; i++) {
            if (storedRecipients[i].equals(recipient)) {
                result.append(storedContents[i]).append("\n");
            }
        }

        if (result.length() > 0) {
            JOptionPane.showMessageDialog(null, result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No messages found for this recipient.");
        }
    }

    private void deleteByHash() {
        String input = JOptionPane.showInputDialog("Enter message hash to delete:");
        int hashToDelete;

        try {
            hashToDelete = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid hash format.");
            return;
        }

        // Search and delete from sent messages
        for (int i = 0; i < sentCount; i++) {
            if (sentHashes[i] == hashToDelete) {
                // Shift all elements down to fill gap
                for (int j = i; j < sentCount - 1; j++) {
                    sentIds[j] = sentIds[j + 1];
                    sentRecipients[j] = sentRecipients[j + 1];
                    sentContents[j] = sentContents[j + 1];
                    sentFlags[j] = sentFlags[j + 1];
                    sentHashes[j] = sentHashes[j + 1];
                }
                sentCount--;
                JOptionPane.showMessageDialog(null, "Message successfully deleted.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Message not found with that hash.");
    }

    private void displayFullReport() {
        if (sentCount == 0) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }

        StringBuilder report = new StringBuilder("Sent Message Report:\n\n");
        for (int i = 0; i < sentCount; i++) {
            report.append("ID: ").append(sentIds[i]).append("\n")
                    .append("Recipient: ").append(sentRecipients[i]).append("\n")
                    .append("Message: ").append(sentContents[i]).append("\n")
                    .append("Flag: ").append(sentFlags[i]).append("\n")
                    .append("Hash: ").append(sentHashes[i]).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }
}
