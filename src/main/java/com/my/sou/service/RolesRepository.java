package com.my.sou.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.my.sou.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {

}

