package com.vsoft.fstudy.feature.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsoft.fstudy.feature.user.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPasswordHash(String email, String passwordHash);
    Optional<User> findByPhone(String phone);
    Optional<User> findByPhoneAndPasswordHash(String phone, String passwordHash);
}
