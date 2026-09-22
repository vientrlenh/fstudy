package com.vsoft.fstudy.feature.auth;

public class AuthMessage {

    private AuthMessage() {}

    public static final String LOGIN_SUCCESS_MSG = "User logged in successfully.";
    public static final String LOGIN_SUCCESS_CODE = "LOGIN_SUCCESS";

    public static final String LOGIN_ERROR_MSG = "Invalid credentials.";
    public static final String USER_NOT_FOUND_ERROR_MSG = "User not found.";

    public static final String EMAIL_ALREADY_EXISTS_ERROR_MSG = "Email already registered.";
    public static final String CONFIRM_PASSWORD_NOT_MATCH = "Password confirmation does not match.";

    public static final String REGISTER_SUCCESS_CODE = "REGISTER_SUCCESS";
    public static final String REGISTER_SUCCESS_MSG = "User registered successfully.";
}
