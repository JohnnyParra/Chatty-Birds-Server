package com.johnnyparra.real_time_chat.types;

import com.johnnyparra.real_time_chat.models.NotificationPreferences;

public class UpdateUserPreferencesInput {
  private String theme;
  private Boolean muteAllChats;
  private String languagePreference;
  private NotificationPreferences notificationPreferences;
  private Boolean twoFaLogin;

  protected UpdateUserPreferencesInput() {}

  public String getTheme() {
    return theme;
  }

  public void setTheme(String theme) {
    this.theme = theme;
  }

  public Boolean getMuteAllChats() {
    return muteAllChats;
  }

  public void setMuteAllChats(Boolean muteAllChats) {
    this.muteAllChats = muteAllChats;
  }

  public String getLanguagePreference() {
    return languagePreference;
  }

  public void setLanguagePreference(String languagePreference) {
    this.languagePreference = languagePreference;
  }

  public NotificationPreferences getNotificationPreferences() {
    return this.notificationPreferences;
  }

  public void setNotificationPreferences(NotificationPreferences notificationPreferences) {
    this.notificationPreferences = notificationPreferences;
  }

  public Boolean getTwoFaLogin() {
    return twoFaLogin;
  }

  public void setTwoFaLogin(Boolean twoFaLogin) {
    this.twoFaLogin = twoFaLogin;
  }
}
