package com.my.sou.dto;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class UserDto {

  private String fullName;

  private String otherName;

  private String idno;

  private Date joinedDate;

  private String email;

  private boolean permanent;

  private String remarks;

  private boolean firstTimeLogin;

  private List<String> phoneNoList;

  private List<AddressDto> addresses;

  private Set<String> roleList;

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

  public List<String> getPhoneNoList() {
    return phoneNoList;
  }

  public void setPhoneNoList(List<String> phoneNoList) {
    this.phoneNoList = phoneNoList;
  }

  public List<AddressDto> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<AddressDto> addresses) {
    this.addresses = addresses;
  }

  public Set<String> getRoleList() {
    return roleList;
  }

  public void setRoleList(Set<String> roleList) {
    this.roleList = roleList;
  }

}
