package com.my.sou.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.my.sou.dto.LoginRq;
import com.my.sou.dto.LoginRs;
import com.my.sou.object.ApiMapping;
import com.my.sou.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

  private final UserService userService;

  public LoginController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping(ApiMapping.Public.LOGIN_POST)
  public ResponseEntity<LoginRs> login(@RequestBody LoginRq request) {
    return ResponseEntity.ok(userService.authenticate(request));
  }

  @PostMapping(ApiMapping.Protected.LOGOUT_POST)
  public ResponseEntity<?> logout(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      userService.logout(header.substring(7));
    }

    return ResponseEntity.ok("Logged out");
  }

}
