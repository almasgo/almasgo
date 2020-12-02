package com.luthfihariz.almasgocore.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ApiKeyGeneratorServiceImpl implements ApiKeyGeneratorService {

    @Override
    public String generate() {
        var timestamp = String.valueOf(System.currentTimeMillis() + (Math.random() * 10000));
        try {
            var messageDigest = MessageDigest.getInstance("SHA-256");
            var encodedHash = messageDigest.digest(timestamp.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String bytesToHex(byte[] hash) {
        var hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            var hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
