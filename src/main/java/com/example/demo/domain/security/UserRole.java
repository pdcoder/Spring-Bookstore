package com.example.demo.domain.security;

import com.example.demo.domain.User;

import javax.persistence.*;

/**
 * Created by prakashdas on 17/08/18.
 */

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;

    public UserRole(User user, Role role) {
    }
}
