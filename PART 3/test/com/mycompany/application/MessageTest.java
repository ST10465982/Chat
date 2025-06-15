/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.application;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageCreationAndGetters() {
        Message msg = new Message("id123", "+27830000001", "Hello there!", "Sent");

        assertEquals("id123", msg.getId());
        assertEquals("+27830000001", msg.getRecipient());
        assertEquals("Hello there!", msg.getContent());
        assertEquals("Sent", msg.getFlag());
        assertEquals("Hello there!".hashCode(), msg.getHash());
    }

    @Test
    public void testMessageSummary() {
        Message msg = new Message("id456", "+27831111111", "Sample", "Stored");
        String summary = msg.getSummary();

        assertTrue(summary.contains("id456"));
        assertTrue(summary.contains("+27831111111"));
        assertTrue(summary.contains("Stored"));
    }
}
