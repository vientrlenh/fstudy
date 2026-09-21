package com.vsoft.fstudy.util.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
    String secret, 
    long expirationMs
) {
    public JwtProperties {
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalStateException("Jwt properties are not configured.");
        }
    }
}
