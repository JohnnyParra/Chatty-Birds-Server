package com.johnnyparra.real_time_chat.models;

import com.johnnyparra.real_time_chat.entities.User;

public class AuthenticationPayload {
  private String token;
  private User user;

  protected AuthenticationPayload() {}

  public AuthenticationPayload(String token, User user) {
    this.user = user;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public User getUser() {
    return user;
  }

}
