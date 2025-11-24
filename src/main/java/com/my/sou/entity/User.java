package com.my.sou.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends ModifiedMetadata implements Serializable {

  private static final long serialVersionUID = 3998077670265370892L;

  @Id
  @Column(name = "USER_ID", nullable = false)
  private String userId;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "FULL_NAME", nullable = false)
  private String fullName;

  @Column(name = "OTHER_NAME")
  private String otherName;

  @Column(name = "IDNO", nullable = false)
  private String idno;

  @Column(name = "JOINED_DATE", nullable = false)
  private Date joinedDate;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "PERMANENT", nullable = false)
  private boolean permanent;

  @Column(name = "REMARKS")
  private String remarks;

  @Column(name = "DELETED", nullable = false)
  private boolean deleted;

  @Column(name = "FIRST_TIME_LOGIN", nullable = false)
  private boolean firstTimeLogin;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PhoneNo> phoneNo;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Address> addresses;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"),
      inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
  private Set<Roles> roles;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getOtherName() {
    return otherName;
  }

  public void setOtherName(String otherName) {
    this.otherName = otherName;
  }

  public String getIdno() {
    return idno;
  }

  public void setIdno(String idno) {
    this.idno = idno;
  }

  public Date getJoinedDate() {
    return joinedDate;
  }

  public void setJoinedDate(Date joinedDate) {
    this.joinedDate = joinedDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isPermanent() {
    return permanent;
  }

  public void setPermanent(boolean permanent) {
    this.permanent = permanent;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public boolean isFirstTimeLogin() {
    return firstTimeLogin;
  }

  public void setFirstTimeLogin(boolean firstTimeLogin) {
    this.firstTimeLogin = firstTimeLogin;
  }

  public List<PhoneNo> getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(List<PhoneNo> phoneNo) {
    this.phoneNo = phoneNo;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public Set<Roles> getRoles() {
    return roles;
  }

  public void setRoles(Set<Roles> roles) {
    this.roles = roles;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", password=" + password + ", fullName=" + fullName
        + ", otherName=" + otherName + ", idno=" + idno + ", joinedDate=" + joinedDate + ", email="
        + email + ", permanent=" + permanent + ", remarks=" + remarks + ", firstTimeLogin="
        + firstTimeLogin + ", phoneNo=" + phoneNo + ", addresses=" + addresses + ", roles=" + roles
        + ", getUserId()=" + getUserId() + ", getPassword()=" + getPassword() + ", getFullName()="
        + getFullName() + ", getOtherName()=" + getOtherName() + ", getIdno()=" + getIdno()
        + ", getJoinedDate()=" + getJoinedDate() + ", getEmail()=" + getEmail() + ", isPermanent()="
        + isPermanent() + ", getRemarks()=" + getRemarks() + ", isFirstTimeLogin()="
        + isFirstTimeLogin() + ", getPhoneNo()=" + getPhoneNo() + ", getAddresses()="
        + getAddresses() + ", getRoles()=" + getRoles() + ", getModifiedBy()=" + getModifiedBy()
        + ", getModifiedOn()=" + getModifiedOn() + ", getCreatedBy()=" + getCreatedBy()
        + ", getCreatedOn()=" + getCreatedOn() + ", getClass()=" + getClass() + ", hashCode()="
        + hashCode() + ", toString()=" + super.toString() + "]";
  }

}
