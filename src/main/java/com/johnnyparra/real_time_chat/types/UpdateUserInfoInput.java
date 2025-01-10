package com.johnnyparra.real_time_chat.types;

public class UpdateUserInfoInput {
  private String firstName;
  private String lastName;
  private String bio;

  protected UpdateUserInfoInput() {}

  public String getFirstName() {
      return firstName;
  }

  public void setFirstName(String firstName) {
      this.firstName = firstName;
  }

  public String getLastName() {
      return lastName;
  }

  public void setLastName(String lastName) {
      this.lastName = lastName;
  }

  public String getBio() {
      return bio;
  }

  public void setBio(String bio) {
      this.bio = bio;
  }
}
