package com.my.sou.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.my.sou.config.JwtUtil;
import com.my.sou.dto.LoginRq;
import com.my.sou.dto.LoginRs;
import com.my.sou.dto.UserDto;

@Service
public class UserService {

  private final AuthenticationManager authenticationManager;

  private final JwtUtil jwtUtil;

  private final SouUserDetailsService userDetailsService;

  private final UserServiceRepo userServiceRepo;

  public UserService(final AuthenticationManager authenticationManager, final JwtUtil jwtUtil,
      final SouUserDetailsService userDetailsService, final UserServiceRepo userServiceRepo) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
    this.userServiceRepo = userServiceRepo;
  }

  public LoginRs authenticate(final LoginRq request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    var user = (org.springframework.security.core.userdetails.User) userDetailsService
        .loadUserByUsername(request.getEmail());

    String token = jwtUtil.generateToken(user);

    final boolean notFirstTimeLogin = userServiceRepo.insertToken(token, user.getUsername());

    return new LoginRs(notFirstTimeLogin ? token : null, !notFirstTimeLogin);
  }

  public void logout(final String jwt) {
    userServiceRepo.logout(jwt);
  }

  public void createUser(UserDto user) {
    userServiceRepo.createUser(user);
  }
}
