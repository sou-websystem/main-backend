package com.my.sou.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.my.sou.dto.UserDto;
import com.my.sou.object.ApiMapping;
import com.my.sou.service.UserService;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping(ApiMapping.Protected.CREATE_USER_POST)
  public ResponseEntity<?> createUser(@RequestBody UserDto user) {
    userService.createUser(user);
    return ResponseEntity.ok("User created");
  }

}
