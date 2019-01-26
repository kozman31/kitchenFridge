package com.thekitchenfridge.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
@Slf4j
public class JwtSecureFields {

    protected final static String SECRET_KEY = generateSecretKey();

    protected static long EXPIRATION_LENGTH = 3600000L;

    protected static String TOKEN_PREFIX = "Bearer";

    private static String generateSecretKey() {
        try {
            KeyGenerator keyGener = KeyGenerator.getInstance("HmacSHA256");
            keyGener.init(512); // here you can pass any valid length
            SecretKey key = keyGener.generateKey();
            return Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
