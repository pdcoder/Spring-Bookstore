package com.example.demo.service.impl;


import com.example.demo.domain.User;
import com.example.demo.domain.security.PasswordResetToken;
import com.example.demo.repository.PasswordResetTokenRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService{


@Autowired
private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResettokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetTokenRepository.save(myToken);
    }
}
