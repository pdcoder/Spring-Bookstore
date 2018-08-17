package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by prakashdas on 17/08/18.
 */
public class UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
