package com.vsoft.fstudy.feature.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.vsoft.fstudy.shared.exception.UnauthorizedException;
import com.vsoft.fstudy.util.properties.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j  
@RequiredArgsConstructor 
public class JwtTokenService {
    
    private final JwtProperties jwtProperties;

    public String generateJwtToken(String userId, String email, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("roles", roles);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.expirationMs());
        return Jwts.builder()
            .claims(claims)
            .subject(userId)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(getSecretKey())
            .compact();
    } 

    public String getEmailFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("email", String.class);
    }

    public UUID getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        String userId = claims.get("userId", String.class);
        return UUID.fromString(userId);
    }

    private SecretKey getSecretKey() {
        String secret = jwtProperties.secret();
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Claims getClaimsFromToken(String token) {
        SecretKey key = getSecretKey();
        try {
            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (ExpiredJwtException e) {
            log.info("JWT token expired: {}", e.getMessage());
            throw new UnauthorizedException("Login expired");
        } catch (JwtException e) {
            log.info("JWT token error: {}", e.getMessage());
            throw new UnauthorizedException("Login error");
        }
    }
}
