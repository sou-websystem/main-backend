package com.my.sou.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.my.sou.dto.UserDto;
import com.my.sou.entity.PhoneNo;
import com.my.sou.entity.Roles;
import com.my.sou.entity.Token;
import com.my.sou.entity.User;
import com.my.sou.object.BadRequestException;

@Service
public class UserServiceRepo {

  private final TokenRepository tokenRepository;

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final RolesRepository rolesRepository;

  private final PasswordEncoder passwordEncoder;

  public UserServiceRepo(final TokenRepository tokenRepository, final UserRepository userRepository,
      final UserMapper userMapper, final RolesRepository rolesRepository,
      final PasswordEncoder passwordEncoder) {
    this.tokenRepository = tokenRepository;
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.rolesRepository = rolesRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public boolean insertToken(final String jwt, final String email) {
    final User user = userRepository.findByEmail(email).get();
    if (user.isFirstTimeLogin()) {
      return false;
    }
    tokenRepository.updateAllTokenRevokedByUser(user);

    Token token = new Token();
    token.setToken(jwt);
    token.setUser(user);
    token.setCreatedOn(Instant.now());
    token.setExpiredAt(Instant.now().plus(1, ChronoUnit.HOURS));
    tokenRepository.save(token);
    return true;
  }

  @Transactional
  public void logout(final String jwt) {
    tokenRepository.findByToken(jwt).ifPresent(token -> {
      token.setRevoked(true);
    });
  }

  @Transactional
  public void createUser(UserDto dto) throws BadRequestException {
    if (userRepository.existsByEmailAndDeletedFalse(dto.getEmail())
        || userRepository.existsByIdnoAndDeletedFalse(dto.getIdno())) {
      throw new BadRequestException("User already exists.");
    }

    User user = userMapper.toEntity(dto);

    List<Roles> roleList = rolesRepository.findAllById(dto.getRoleList());
    if (dto.getRoleList() == null || dto.getRoleList().isEmpty()
        || roleList.size() != dto.getRoleList().size()) {
      throw new BadRequestException("User Roles Invalid");
    }

    Set<Roles> roleSet = new LinkedHashSet<>(roleList);
    user.setRoles(roleSet);

    List<PhoneNo> phoneList = new ArrayList<>();
    dto.getPhoneNoList().forEach(i -> {
      PhoneNo phone = new PhoneNo();
      phone.setPhoneNo(i);
      phone.setUser(user);
      phoneList.add(phone);
    });
    user.setPhoneNo(phoneList);

    user.setFirstTimeLogin(true);
    user.setPassword(passwordEncoder.encode("sou1234"));
    user.getAddresses().forEach(i -> i.setUser(user));

    userRepository.save(user);
  }
}
