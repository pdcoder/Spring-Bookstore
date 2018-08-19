package com.example.demo.service.impl;


import com.example.demo.domain.User;
import com.example.demo.domain.security.PasswordResetToken;
import com.example.demo.domain.security.UserRole;
import com.example.demo.repository.PasswordResetTokenRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl  implements UserService{


@Autowired
private PasswordResetTokenRepository passwordResetTokenRepository;

@Autowired
private JavaMailSender mailSender;

@Autowired
private RoleRepository roleRepository;

@Autowired
private UserRepository userRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token,user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public User findByUsername(String username){
        return  userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email){
        return  userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws  Exception{
        User localUser = userRepository.findByUsername(user.getUsername());
        if(localUser!=null){
            throw new Exception("User already register. Nothing to do.");
        }
        else {
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            localUser = userRepository.save(user);
        }
        return localUser;
    }

}
