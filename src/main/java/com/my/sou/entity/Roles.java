package com.my.sou.entity;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Roles implements Serializable {

  private static final long serialVersionUID = -5648852520821128694L;

  @Id
  @Column(name = "ROLE_ID", nullable = false)
  private String roldId;

  @Column(name = "TITLE")
  private String title;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  public String getRoldId() {
    return roldId;
  }

  public void setRoldId(String roldId) {
    this.roldId = roldId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

}
