package com.vsoft.fstudy.util.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.cors")
public record CorsProperties(
    List<String> allowedOrigins
) {
    public CorsProperties { 
        allowedOrigins = allowedOrigins == null 
            ? List.of()
            : allowedOrigins.stream()
                .map(origin -> origin.trim())
                .filter(origin -> !origin.isEmpty())
                .toList();
    }
}
