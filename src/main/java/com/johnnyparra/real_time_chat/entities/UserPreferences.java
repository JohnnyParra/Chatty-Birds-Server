package com.johnnyparra.real_time_chat.entities;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicUpdate;

import com.johnnyparra.real_time_chat.converters.NotificationPreferencesConverter;
import com.johnnyparra.real_time_chat.models.NotificationPreferences;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_preferences")
@DynamicUpdate
public class UserPreferences {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  @Column(name = "theme", columnDefinition = "TEXT", nullable = false)
  private String theme = "Dark";

  @Column(name = "mute_all_chats", nullable = false)
  private Boolean muteAllChats = false;

  @Column(name = "language_preference", nullable = false)
  private String languagePreference = "English";

  @Column(name = "notification_preferences", columnDefinition = "jsonb", nullable = true)
  @Convert(converter = NotificationPreferencesConverter.class)
  @ColumnTransformer(write = "?::jsonb")
  private NotificationPreferences notificationPreferences;

  @Column(name = "notification_preferences", insertable = false, updatable = false)
  private String rawNotificationPreferences;

  @Column(name="two_factor_login", nullable = false)
  private Boolean twoFaLogin = false;

  public UserPreferences() {
    this.theme = "Dark";
    this.muteAllChats = false;
    this.languagePreference = "English";
    this.notificationPreferences = new NotificationPreferences();
    this.twoFaLogin = false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

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
    return notificationPreferences;
  }

  public void setNotificationPreferences(NotificationPreferences notificationPreferences) {
    this.notificationPreferences = notificationPreferences;
  }

  public String getRawAvatarMetadata() {
    return rawNotificationPreferences;
  }

  public Boolean getTwoFaLogin() {
    return twoFaLogin;
  }

  public void setTwoFaLogin(Boolean twoFaLogin) {
    this.twoFaLogin = twoFaLogin;
  }
}

