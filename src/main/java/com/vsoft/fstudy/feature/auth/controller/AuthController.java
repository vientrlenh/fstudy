package com.vsoft.fstudy.feature.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.fstudy.feature.auth.AuthMessage;
import com.vsoft.fstudy.feature.auth.dto.request.LoginRequest;
import com.vsoft.fstudy.feature.auth.dto.request.RegisterRequest;
import com.vsoft.fstudy.feature.auth.dto.response.LoginResponse;
import com.vsoft.fstudy.feature.auth.service.AuthService;
import com.vsoft.fstudy.shared.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor 
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        ApiResponse<LoginResponse> apiResponse = ApiResponse.success(
            AuthMessage.LOGIN_SUCCESS_CODE, 
            AuthMessage.LOGIN_SUCCESS_MSG, 
            response
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        ApiResponse<Void> apiResponse = ApiResponse.success(AuthMessage.REGISTER_SUCCESS_CODE, AuthMessage.REGISTER_SUCCESS_MSG);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
