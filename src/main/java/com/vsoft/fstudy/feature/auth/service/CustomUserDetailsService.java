package com.vsoft.fstudy.feature.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vsoft.fstudy.feature.auth.CustomUserDetails;
import com.vsoft.fstudy.feature.auth.AuthMessage;
import com.vsoft.fstudy.feature.user.UserRepository;
import com.vsoft.fstudy.feature.user.model.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        if (username.contains("@")) {
            user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(AuthMessage.LOGIN_ERROR_MSG));
        } else {
            user = userRepository.findByPhone(username)
                .orElseThrow(() -> new UsernameNotFoundException(AuthMessage.LOGIN_ERROR_MSG));
        }
        return CustomUserDetails.create(user);
    }
    
}
