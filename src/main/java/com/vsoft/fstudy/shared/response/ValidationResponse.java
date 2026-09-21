package com.vsoft.fstudy.shared.response;

import java.util.Map;

public record ValidationResponse(
    Map<String, String> errors, 
    String code
) {
    public static ValidationResponse error(Map<String, String> errors) {
        return new ValidationResponse(errors, "VALIDATION_ERROR");
    }
}
