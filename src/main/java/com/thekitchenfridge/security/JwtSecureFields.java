package com.thekitchenfridge.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@Slf4j
public class JwtSecureFields {

    protected static String SECRET_KEY;
    protected static String TOKEN_PREFIX;
    protected static Long EXPIRATION_LENGTH;

    @Value("${security.jwt.token.secret-key}")
    private void setSecretKey(String secretKey)  {
        JwtSecureFields.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Value("${security.jwt.token.token-prefix}")
    private void setTokenPrefix(String tokenPrefix)  {
        JwtSecureFields.TOKEN_PREFIX = tokenPrefix;
    }

    @Value("${security.jwt.token.expiration-length}")
    private void setExpirationLen(String expTime)  {
        JwtSecureFields.EXPIRATION_LENGTH = Long.valueOf(expTime);
    }
}

