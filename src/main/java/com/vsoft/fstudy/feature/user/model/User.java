package com.vsoft.fstudy.feature.user.model;

import java.time.LocalDate;

import com.vsoft.fstudy.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "users")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class User extends BaseEntity {
    @Column(name = "email", nullable = false, updatable = false, length = 255, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(name = "phone", length = 30, unique = true)
    private String phone;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", nullable = false, updatable = false)
    private LocalDate birthDate;

    @Column(name = "address", length = 512)
    private String address;

    @Column(name = "avatar_url", length = 2048)
    private String avatarUrl;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;
}
