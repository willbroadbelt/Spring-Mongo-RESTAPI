package com.will.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class UIValidationTest {
    UserInputValidation u = new UserInputValidation();

    @Test
    public void emailTestNotValid(){
        assertFalse(u.userEmail(""));
        assertFalse(u.userEmail("thisemail.com"));
    }

    @Test
    public void emailTestValid(){
        assertTrue(u.userEmail("person@gmail.com"));
        assertTrue(u.userEmail("phill@mail.co.uk"));
    }

    @Test
    public void passwordTestValid(){
        assertTrue(u.userPassword("random1"));
    }

    public void passwordTestNotValid(){
        assertFalse(u.userPassword("123456"));
    }
}
