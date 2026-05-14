package com.ecommerce.gateway_service.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET = "ThisIsA256BitSecretKeyForJwtMustBeSameEverywhere123";

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT");
        }
    }
}

