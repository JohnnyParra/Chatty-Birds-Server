package com.johnnyparra.real_time_chat.types;

public class UpdateSensitiveUserInfoInput {
  private String password;
  private String username;
  private String email;
  private String phoneNumber;
  private String newPassword;

  protected UpdateSensitiveUserInfoInput() {}

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getPhoneNumber() {
      return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
  }

  public String getNewPassword() {
      return newPassword;
  }

  public void setNewPassword(String newPassword) {
      this.newPassword = newPassword;
  }
}
