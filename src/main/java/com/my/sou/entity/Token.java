package com.my.sou.entity;

import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TOKENS")
public class Token extends CreatedMetadata {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "TOKEN", unique = true, length = 500)
  private String token;

  @Column(name = "EXPIRED_AT")
  private Instant expiredAt;

  @Column(name = "REVOKED")
  private boolean revoked;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Instant getExpiredAt() {
    return expiredAt;
  }

  public void setExpiredAt(Instant expiredAt) {
    this.expiredAt = expiredAt;
  }

  public boolean isRevoked() {
    return revoked;
  }

  public void setRevoked(boolean revoked) {
    this.revoked = revoked;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}

