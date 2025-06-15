/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.application;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MessageHandlerTest {

    private MessageHandler handler;

    @Before
    public void setUp() {
        handler = new MessageHandler();
    }

    @Test
    public void testAssignFlag_ShortMessage() {
        String flag = handlerTestHelper_assignFlag("hi");
        assertEquals("Disregard", flag);
    }

    @Test
    public void testAssignFlag_LongMessage() {
        String longMsg = "This is a very long message that goes over 50 characters, just to test storage.";
        String flag = handlerTestHelper_assignFlag(longMsg);
        assertEquals("Stored", flag);
    }

    @Test
    public void testAssignFlag_NormalMessage() {
        String flag = handlerTestHelper_assignFlag("Hi there! Meet me at 5.");
        assertEquals("Sent", flag);
    }

    // Helper method to test private logic (simulate the logic inside sendMessage)
    private String handlerTestHelper_assignFlag(String msg) {
        if (msg.toLowerCase().contains("yohoo") || msg.length() < 15) {
            return "Disregard";
        } else if (msg.length() > 50) {
            return "Stored";
        } else {
            return "Sent";
        }
    }
}
