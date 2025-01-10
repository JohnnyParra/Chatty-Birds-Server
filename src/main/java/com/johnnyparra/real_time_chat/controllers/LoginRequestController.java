package com.johnnyparra.real_time_chat.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.johnnyparra.real_time_chat.entities.User;
import com.johnnyparra.real_time_chat.models.AuthenticationPayload;
import com.johnnyparra.real_time_chat.models.LoginRequestDTO;
import com.johnnyparra.real_time_chat.repositories.UserRepository;
import com.johnnyparra.real_time_chat.services.JwtUtility;

import jakarta.servlet.http.HttpServletResponse;


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

  private void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
    ResponseCookie cookie = ResponseCookie.from(name, value)
      .httpOnly(true)
      .secure(true)
      .maxAge(maxAge)
      .path("/")
      .build();
    response.addHeader("Set-Cookie", cookie.toString());
  }

  @MutationMapping
  public AuthenticationPayload logIn(@Argument LoginRequestDTO loginRequest, HttpServletResponse response) {
    User user = userRepository.findByEmail(loginRequest.getEmail());
    if (user == null) {
      throw new RuntimeException("Invalid email or password");
    }

    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid email or password");
    }

    String jwtToken = JwtUtility.generateJwtToken(user);
    String refreshToken = JwtUtility.generateRefreshToken();

    setCookie(response, "jwt", jwtToken, 900);
    setCookie(response, "refreshToken", refreshToken,  604800);

    return new AuthenticationPayload(jwtToken, user);
  }
}
