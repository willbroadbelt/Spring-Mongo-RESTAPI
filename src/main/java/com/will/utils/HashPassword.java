package com.will.utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

//Based on example found at https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
public class HashPassword {
    /**
     * @param password
     * @return list containing the hashed password as the first item and the salt used as the second
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static ArrayList<String> generatePasswordHash(String password) {
        int iterations = 1000;
        int keyLength = 64 * 8;
        char[] passwordChars = password.toCharArray();
        byte[] salt = getSalt();

        ArrayList<String> passwordAndSalt = new ArrayList<String>();
        String passwordHash = hashPassword(passwordChars,salt,iterations,keyLength);
        String saltString = toHex(salt);
        passwordAndSalt.add(passwordHash);
        passwordAndSalt.add(saltString);
        return passwordAndSalt;
    }

    public static boolean checkPassword(String password, String passwordHash, String salt) {
        byte[] saltBytes = fromHex(salt);
        String hashedNew = getPasswordHashToCheck(password,saltBytes);
        return hashedNew.equals(passwordHash);
    }

    private static String getPasswordHashToCheck(String password, byte[] salt) {
        int iterations = 1000;
        int keyLength = 64 * 8;
        char[] passwordChars = password.toCharArray();

        return hashPassword(passwordChars,salt,iterations,keyLength);
    }

    private static byte[] getSalt() {
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    private static String hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] hash = key.getEncoded( );
            return toHex(hash);

        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
}

