package com.vsoft.fstudy.feature.auth;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vsoft.fstudy.feature.user.model.User;
import com.vsoft.fstudy.feature.user.model.UserStatus;

import lombok.Builder;

@Builder 
public class CustomUserDetails implements UserDetails {
    private UUID id;
    private String email;
    private String password;
    private UserStatus status;
    private Collection<? extends GrantedAuthority> authorities;

    private static final String AUTHORITY_ROLE_PREFIX = "ROLE_";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    @Override 
    public boolean isEnabled() {
        return status == UserStatus.ACTIVE || status == UserStatus.INACTIVE;
    }

    public static CustomUserDetails create(User user) {
        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(AUTHORITY_ROLE_PREFIX + user.getRole().name()));
        return CustomUserDetails.builder()
            .id(user.getId())
            .email(user.getEmail())
            .password(user.getPasswordHash())
            .status(user.getStatus())
            .authorities(authorities)
            .build();
    }
}
