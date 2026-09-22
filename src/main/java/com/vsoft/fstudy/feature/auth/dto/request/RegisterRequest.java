package com.vsoft.fstudy.feature.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

    @NotBlank(message = "Email address is required.")
    @Size(max = 255, message = "Email address must not exceed 255 characters.")
    @Email(message = "Invalid email address.")
    String email, 

    @NotBlank(message = "Full name is required.")
    @Size(max = 255, message = "Full name must not exceed 255 characters.")
    String fullName, 

    @NotBlank(message = "Birth date is required.")
    String birthDate, 

    @NotBlank(message = "Password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters.")
    String password, 

    @NotBlank(message = "Password confirmation is required.")
    String confirmPassword
) {
    
}
