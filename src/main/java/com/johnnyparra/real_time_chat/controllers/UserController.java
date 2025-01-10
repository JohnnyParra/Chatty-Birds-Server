package com.johnnyparra.real_time_chat.controllers;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.johnnyparra.real_time_chat.entities.User;
import com.johnnyparra.real_time_chat.entities.UserPreferences;
import com.johnnyparra.real_time_chat.repositories.UserRepository;
import com.johnnyparra.real_time_chat.types.UpdateSensitiveUserInfoInput;
import com.johnnyparra.real_time_chat.types.UpdateUserInfoInput;

@Controller
public class UserController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @QueryMapping
  public User userById(@Argument Long id) {
    System.out.println("Fetching user with ID: " + id);
    return userRepository.findById(id).orElse(null);
  }

  @MutationMapping
  public User createUser(
    @Argument String username, 
    @Argument String email, 
    @Argument String password) {

    System.out.println("Creating user: " + username + ", " + email);
    String hashedPassword = passwordEncoder.encode(password);
    User user = new User(username, email, hashedPassword);

    UserPreferences userPreferences = new UserPreferences();
    userPreferences.setUser(user);
    user.getUserPreferences().add(userPreferences);
    
    return userRepository.save(user);
  }

  @MutationMapping  
  public User updateUserInfo(
    @Argument Long id, 
    @Argument UpdateUserInfoInput input) {
    String firstName = input.getFirstName();
    String lastName = input.getLastName();
    String bio = input.getBio();

    System.out.println("updating user: " + id );

    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isEmpty()) {
      throw new RuntimeException("User not found with id: " + id);
    }
    User user = optionalUser.get();
    
    if (firstName != null) {
      user.setFirstName(firstName);
    }
    if (lastName != null) {
      user.setLastName(lastName);
    }
    if (bio != null) {
      user.setBio(bio);
    }
    user.setUpdatedAt(ZonedDateTime.now());

    return userRepository.save(user);
  }

  @MutationMapping
  public User updateSensitiveUserInfo(
    @Argument Long id, 
    @Argument UpdateSensitiveUserInfoInput input) {

    String password = input.getPassword();
    String username = input.getUsername();
    String email = input.getEmail();
    String phoneNumber = input.getPhoneNumber();
    String newPassword = input.getNewPassword();

    System.out.println("updating sensitive info of user: " + id);

    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isEmpty()) {
      throw new RuntimeException("User not found with id: " + id);
    }
    User user = optionalUser.get();
    
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Invalid current password.");
    }

    if (username != null) {
      user.setUsername(username);
    }
    if (email != null) {
      user.setEmail(email);
    }
    if (phoneNumber != null) {
      user.setPhoneNumber(phoneNumber);
    }
    if (newPassword != null) {
      user.setPassword(passwordEncoder.encode(newPassword));
    }
    user.setUpdatedAt(ZonedDateTime.now());

    return userRepository.save(user);
  }
}
