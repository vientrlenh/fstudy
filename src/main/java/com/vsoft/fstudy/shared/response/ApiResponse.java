package com.vsoft.fstudy.shared.response;

public record ApiResponse<T>(
    String code, 
    String message, 
    T data
) {
    public static <T> ApiResponse<T> success(String code, String message, T data) {
        return new ApiResponse<T>(code, message, data);
    }

    public static <T> ApiResponse<T> success(String code, String message) {
        return new ApiResponse<T>(code, message, null);
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<T>(code, message, null);
    }
}
