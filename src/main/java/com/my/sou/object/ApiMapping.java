package com.my.sou.object;

public class ApiMapping {

  private static final String PUBLIC = "/public";

  private static final String PROTECTED = "/protected";

  private static final String AUTH = "/auth";

  public class Public {

    public static final String LOGIN_POST = PUBLIC + AUTH + "/login";

  }

  public class Protected {

    public static final String LOGOUT_POST = PROTECTED + AUTH + "/logout";

    public static final String CREATE_USER_POST = PROTECTED + "/user/create";

  }

}
