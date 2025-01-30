package com.johnnyparra.real_time_chat.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.johnnyparra.real_time_chat.entities.User;
import com.johnnyparra.real_time_chat.models.AuthenticationPayload;
import com.johnnyparra.real_time_chat.models.LoginRequestDTO;
import com.johnnyparra.real_time_chat.repositories.UserRepository;
import com.johnnyparra.real_time_chat.services.JwtUtility;

import graphql.GraphQLContext;


@Controller
public class LoginRequestController {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private JwtUtility JwtUtility;

  public LoginRequestController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtility JwtUtility) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.JwtUtility = JwtUtility;
  }

  @MutationMapping
  @PreAuthorize("permitAll()")
  public AuthenticationPayload logIn(@Argument LoginRequestDTO loginRequest, GraphQLContext context) {
    User user = userRepository.findByEmail(loginRequest.getEmail());
    if (user == null) {
      throw new RuntimeException("Invalid email or password");
    }

    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid email or password");
    }

    String jwtToken = JwtUtility.generateJwtToken(user);
    String refreshToken = JwtUtility.generateRefreshToken(user);

    context.put("jwt", jwtToken);
    context.put("refreshToken", refreshToken);

    return new AuthenticationPayload(jwtToken, user);
  }
}
