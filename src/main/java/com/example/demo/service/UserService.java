package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.security.PasswordResetToken;

/**
 * Created by prakashdas on 18/08/18.
 */
public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username);

    User findByEmail(String email);
}
