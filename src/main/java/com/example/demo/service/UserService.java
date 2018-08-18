package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.security.PasswordResetToken;
import com.example.demo.domain.security.UserRole;

import java.util.Set;

/**
 * Created by prakashdas on 18/08/18.
 */
public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username);

    User findByEmail(String email);

    User createUser(User user, Set<UserRole> userRoles);
}
