package com.my.sou.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.my.sou.entity.Token;
import com.my.sou.entity.User;

public interface TokenRepository extends JpaRepository<Token, String> {

  Optional<Token> findByToken(String token);

  List<Token> findAllByUserAndRevokedFalse(User user);

  @Modifying
  @Query("UPDATE Token t set t.revoked = true where t.user = :user")
  int updateAllTokenRevokedByUser(@Param("user") User user);
}

