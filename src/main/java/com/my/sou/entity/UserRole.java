package com.my.sou.entity;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {

  private static final long serialVersionUID = -4497778418952633306L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
  private User user;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
  private Roles role;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }

}
