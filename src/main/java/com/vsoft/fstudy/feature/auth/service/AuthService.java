package com.vsoft.fstudy.feature.auth.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsoft.fstudy.feature.auth.CustomUserDetails;
import com.vsoft.fstudy.feature.auth.AuthMessage;
import com.vsoft.fstudy.feature.auth.AuthRequestNormalization;
import com.vsoft.fstudy.feature.auth.dto.request.LoginRequest;
import com.vsoft.fstudy.feature.auth.dto.request.RegisterRequest;
import com.vsoft.fstudy.feature.auth.dto.response.LoginResponse;
import com.vsoft.fstudy.feature.user.UserRepository;
import com.vsoft.fstudy.feature.user.model.User;
import com.vsoft.fstudy.shared.DateMapper;
import com.vsoft.fstudy.shared.exception.DuplicatedException;
import com.vsoft.fstudy.shared.exception.NotFoundException;
import com.vsoft.fstudy.shared.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor
@Slf4j  
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest request) {
        LoginRequest normalized = AuthRequestNormalization.normalize(request);
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(normalized.login(), normalized.password()));
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        if (userDetails == null) {
            throw new UnauthorizedException(AuthMessage.LOGIN_ERROR_MSG);
        }
        User user = userRepository.findById(userDetails.getId())
            .orElseThrow(() -> new NotFoundException(AuthMessage.USER_NOT_FOUND_ERROR_MSG));
        String accessToken = jwtTokenService.generateJwtToken(user.getId().toString(), user.getEmail(), List.of(user.getRole().name()));
        return new LoginResponse(accessToken);
    }

    @Transactional 
    public Void register(RegisterRequest request) {
        RegisterRequest normalized = AuthRequestNormalization.normalize(request);
        if (userRepository.existsByEmail(normalized.email())) {
            throw new DuplicatedException(AuthMessage.EMAIL_ALREADY_EXISTS_ERROR_MSG);
        }
        if (!normalized.password().equals(normalized.confirmPassword())) {
            throw new IllegalArgumentException(AuthMessage.CONFIRM_PASSWORD_NOT_MATCH);
        }
        LocalDate birthDate = DateMapper.toLocalDate(normalized.birthDate());
        try {
            User newUser = User.createLearner(
                normalized.email(), 
                passwordEncoder.encode(normalized.password()), 
                normalized.fullName(), 
                birthDate
            );
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            log.error("Data violation in register: {}", e.getMessage());
            throw new DuplicatedException(AuthMessage.EMAIL_ALREADY_EXISTS_ERROR_MSG);
        }
        return null;
    }
}
