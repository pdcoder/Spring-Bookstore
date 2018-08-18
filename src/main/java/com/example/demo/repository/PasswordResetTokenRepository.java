package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

/**
 * Created by prakashdas on 18/08/18.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long>{

    PasswordResetToken findByUser(User user);
    PasswordResetToken findByToken(String token);
    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1");
    void deleteAllExpiredSince(Date now);
}
