package com.vsoft.fstudy.feature.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Email or phone number is required.")
    String login, 

    @NotBlank(message = "Password is required.")
    String password
) {
    
}
