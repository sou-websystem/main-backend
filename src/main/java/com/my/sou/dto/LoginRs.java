package com.my.sou.dto;

public class LoginRs {

  private String token;

  private boolean firstTimeLogin;

  public LoginRs(String token, boolean firstTimeLogin) {
    this.token = token;
    this.firstTimeLogin = firstTimeLogin;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isFirstTimeLogin() {
    return firstTimeLogin;
  }

  public void setFirstTimeLogin(boolean firstTimeLogin) {
    this.firstTimeLogin = firstTimeLogin;
  }

}
