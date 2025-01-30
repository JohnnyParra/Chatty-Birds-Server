package com.johnnyparra.real_time_chat.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.johnnyparra.real_time_chat.entities.UserPreferences;
import com.johnnyparra.real_time_chat.models.NotificationPreferences;
import com.johnnyparra.real_time_chat.repositories.UserPreferencesRepository;
import com.johnnyparra.real_time_chat.types.UpdateUserPreferencesInput;


@Controller
public class UserPreferencesController {

  private final UserPreferencesRepository userPreferencesRepository;

  public UserPreferencesController(UserPreferencesRepository userPreferencesRepository) {
    this.userPreferencesRepository = userPreferencesRepository;
  }

  @QueryMapping
  @PreAuthorize("isAuthenticated() and #id = principal.id")
  public UserPreferences userPreferencesById(@Argument Long id) {
    System.out.println("Fetching user preferences with ID: " + id);
    return userPreferencesRepository.findById(id).orElse(null);
  }

  @MutationMapping
  @PreAuthorize("isAuthenticated() and #id = principal.id")
  public UserPreferences updateUserPreferences(
    @Argument Long id, 
    @Argument UpdateUserPreferencesInput input) {

    String theme = input.getTheme();
    Boolean muteAllChats = input.getMuteAllChats();
    String languagePreference = input.getLanguagePreference();
    NotificationPreferences notificationPreferences = input.getNotificationPreferences();
    Boolean twoFaLogin = input.getTwoFaLogin();

    System.out.println("Updating user preferences: " + id);
    UserPreferences userPreferences = userPreferencesRepository.findById(id).orElse(null);
    if (userPreferences == null) {
      return null;
    }

    if (theme != null) {
      userPreferences.setTheme(theme);
    }
    if (muteAllChats != null) {
      userPreferences.setMuteAllChats(muteAllChats);
    }
    if (languagePreference != null) {
      userPreferences.setLanguagePreference(languagePreference);
    }
    if (notificationPreferences != null) {
      userPreferences.setNotificationPreferences(notificationPreferences);
    }
    if (twoFaLogin != null) {
      userPreferences.setTwoFaLogin(twoFaLogin);
    }

    return userPreferencesRepository.save(userPreferences);
  }
}
