package com.example.fortuno_api.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.fortuno_api.models.User;

@Service
public class TokenJWTService {
    
    private String secret = "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgFdMV1uDigtID12Vx3k7n9HqEYnm";

    public String generateToken(User user) throws JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer("fortuno-api")
            .withSubject(user.getUsername())
            .withExpiresAt(getTokenExpireDate())
            .sign(algorithm);
    }

    public String tokenValidator(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
            .withIssuer("fortuno-api")
            .build()
            .verify(token)
            .getSubject();
    }

    private Instant getTokenExpireDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
