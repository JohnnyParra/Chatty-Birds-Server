package com.johnnyparra.real_time_chat.models;

public class LoginRequestDTO {
  private String email;
  private String password;

  protected LoginRequestDTO() {}

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
