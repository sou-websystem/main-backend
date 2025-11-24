package com.my.sou.service;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.my.sou.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByEmail(String email);

  boolean existsByEmailAndDeletedFalse(String email);

  boolean existsByIdnoAndDeletedFalse(String email);
}
