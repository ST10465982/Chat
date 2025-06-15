package com.mycompany.application;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    static Login login;

    @BeforeClass
    public static void setUpClass() {
        login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Emily");
    }

    @AfterClass
    public static void tearDownClass() {
        login = null;
    }

    @Test
    public void testCheckUserName_Correct() {
        assertTrue(login.checkUserName());
    }

    @Test
    public void testCheckUserName_Incorrect() {
        Login testLogin = new Login("kyle!!!!!!", "Password1!", "+27838968976", "Test", "User");
        assertFalse(testLogin.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity_Correct() {
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_Incorrect() {
        Login testLogin = new Login("user_", "password", "+27830000000", "Test", "User");
        assertFalse(testLogin.checkPasswordComplexity());
    }

    @Test
    public void testCheckCellPhoneNumber_Correct() {
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_Incorrect() {
        Login testLogin = new Login("user_", "Password1!", "08966553", "Test", "User");
        assertFalse(testLogin.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUser_Success() {
        Login testLogin = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Emily");
        String message = testLogin.registerUser();
        assertEquals("User registered successfully.", message);
    }

    @Test
    public void testLoginUser_Success() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_Failure() {
        assertFalse(login.loginUser("wrongUser", "wrongPass"));
    }

    @Test
    public void testReturnLoginStatus_Success() {
        String message = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Emily it is great to see you again.", message);
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        String message = login.returnLoginStatus("kyl_1", "wrongPass");
        assertEquals("Username or password incorrect, please try again.", message);
    }
}
