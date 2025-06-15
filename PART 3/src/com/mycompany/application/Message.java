/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.application;

public class Message {
    private String id;
    private String recipient;
    private String content;
    private String flag;
    private int hash;

    public Message(String id, String recipient, String content, String flag) {
        this.id = id;
        this.recipient = recipient;
        this.content = content;
        this.flag = flag;
        this.hash = content.hashCode();
    }

    public String getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public String getFlag() {
        return flag;
    }

    public int getHash() {
        return hash;
    }

    public String getSummary() {
        return "ID: " + id + "\nRecipient: " + recipient + "\nMessage: " + content + "\nFlag: " + flag + "\nHash: " + hash;
    }
}
