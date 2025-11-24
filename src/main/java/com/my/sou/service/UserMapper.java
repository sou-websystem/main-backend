package com.my.sou.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.my.sou.dto.UserDto;
import com.my.sou.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "userId", expression = "java(java.util.UUID.randomUUID().toString())")
  User toEntity(UserDto user);
}
