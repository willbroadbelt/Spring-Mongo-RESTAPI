package com.will.utils;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class HashPasswordTest {
    /*
     * generatePasswordHash
     *
     * checkPassword
     *
     */

    @Test
    public void checkPasswordEqualTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "password";
        ArrayList<String> passwordDetails = HashPassword.generatePasswordHash(password);
        String passwordHash = passwordDetails.get(0);
        String salt = passwordDetails.get(1);
        assertTrue(HashPassword.checkPassword(password,passwordHash,salt));
    }
}
