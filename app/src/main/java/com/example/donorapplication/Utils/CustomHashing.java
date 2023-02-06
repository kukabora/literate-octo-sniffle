package com.example.donorapplication.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomHashing {

    public static String sha256(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        String hash = new String(encodedhash);
        return hash;
    }

}
