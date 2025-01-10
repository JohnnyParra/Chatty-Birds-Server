package com.johnnyparra.real_time_chat.converters;

import com.johnnyparra.real_time_chat.models.NotificationPreferences;

public class NotificationPreferencesConverter extends JsonbConverter<NotificationPreferences> {

  public NotificationPreferencesConverter() {
    super(NotificationPreferences.class);
  }

}
