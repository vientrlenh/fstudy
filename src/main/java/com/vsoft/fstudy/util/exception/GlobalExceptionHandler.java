package com.vsoft.fstudy.util.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vsoft.fstudy.shared.exception.NotFoundException;
import com.vsoft.fstudy.shared.exception.UnauthorizedException;
import com.vsoft.fstudy.shared.response.ApiResponse;
import com.vsoft.fstudy.shared.response.ValidationResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages = "com.vsoft.fstudy")
@Slf4j 
public class GlobalExceptionHandler {

    private static final String ILLEGAL_ARGUMENT_EX_CODE = "BAD_REQUEST";
    private static final String UNAUTHORIZED_EX_CODE = "UNAUTHORIZED";
    private static final String NOTFOUND_EX_CODE = "NOT_FOUND";
    private static final String AUTHENTICATION_EX_CODE = "AUTHENTICATION_FAILED";
    private static final String ACCESS_DENIED_EX_CODE = "FORBIDDEN";
    private static final String DISABLED_EX_CODE = "USER_DISABLED";
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponse> handleValidation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult()
            .getAllErrors()
            .forEach(err -> errors.put(err.getObjectName(), err.getDefaultMessage()));
        ValidationResponse error = ValidationResponse.error(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException e) {
        ApiResponse<Void> error = ApiResponse.error(ILLEGAL_ARGUMENT_EX_CODE, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnauthorized(UnauthorizedException e) {
        ApiResponse<Void> error = ApiResponse.error(UNAUTHORIZED_EX_CODE, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NotFoundException e) {
        ApiResponse<Void> error = ApiResponse.error(NOTFOUND_EX_CODE, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuthentication(AuthenticationException e) {
        ApiResponse<Void> error = ApiResponse.error(AUTHENTICATION_EX_CODE, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDenined(AccessDeniedException e) {
        ApiResponse<Void> error = ApiResponse.error(ACCESS_DENIED_EX_CODE, e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResponse<Void>> handleDisabled(DisabledException e) {
        ApiResponse<Void> error = ApiResponse.error(DISABLED_EX_CODE, e.getMessage());
        return ResponseEntity.status(HttpStatus.LOCKED).body(error);
    }
}
