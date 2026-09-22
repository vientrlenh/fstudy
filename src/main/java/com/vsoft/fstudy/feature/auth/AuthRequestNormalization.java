package com.vsoft.fstudy.feature.auth;

import com.vsoft.fstudy.feature.auth.dto.request.LoginRequest;
import com.vsoft.fstudy.feature.auth.dto.request.RegisterRequest;
import com.vsoft.fstudy.shared.StringNormalization;

public class AuthRequestNormalization {

    private AuthRequestNormalization() {}
    
    public static LoginRequest normalize(LoginRequest request) {
        String login;
        if (request.login().contains("@")) {
            login = StringNormalization.normalizeEmail(request.login());
        } else {
            login = StringNormalization.normalizePhone(request.login());
        }
        return new LoginRequest(
            login, 
            request.password()
        );
    }

    public static RegisterRequest normalize(RegisterRequest request) {
        return new RegisterRequest(
            StringNormalization.normalizeEmail(request.email()), 
            StringNormalization.trimAndCollapseSpace(request.fullName()), 
            StringNormalization.trimAndCollapseSpace(request.birthDate()), 
            request.password(), 
            request.confirmPassword()
        );
    }
}
