package com.vsoft.fstudy.feature.auth.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.vsoft.fstudy.feature.auth.CustomUserDetails;
import com.vsoft.fstudy.feature.auth.AuthMessage;
import com.vsoft.fstudy.feature.auth.dto.request.LoginRequest;
import com.vsoft.fstudy.feature.auth.dto.response.LoginResponse;
import com.vsoft.fstudy.feature.user.UserRepository;
import com.vsoft.fstudy.feature.user.model.User;
import com.vsoft.fstudy.shared.exception.NotFoundException;
import com.vsoft.fstudy.shared.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.login(), request.password()));
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        if (userDetails == null) {
            throw new UnauthorizedException(AuthMessage.LOGIN_ERROR_MSG);
        }
        User user = userRepository.findById(userDetails.getId())
            .orElseThrow(() -> new NotFoundException("User not found."));
        String accessToken = jwtTokenService.generateJwtToken(user.getId().toString(), user.getEmail(), List.of(user.getRole().name()));
        return new LoginResponse(accessToken);
    }


}
