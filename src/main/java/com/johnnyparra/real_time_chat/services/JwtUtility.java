package com.johnnyparra.real_time_chat.services;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Component;

import com.johnnyparra.real_time_chat.entities.RefreshTokens;
import com.johnnyparra.real_time_chat.entities.User;
import com.johnnyparra.real_time_chat.repositories.RefreshTokensRepository;

@Component
public class JwtUtility {

  private static final SecretKey SECRET_Key = Jwts.SIG.HS512.key().build();
  private static final long JWT_EXPIRATION_TIME = 900000; // 15 minutes
  private final RefreshTokensRepository refreshTokensRepository;

  public JwtUtility(RefreshTokensRepository refreshTokensRepository) {
    this.refreshTokensRepository = refreshTokensRepository;
  }

  public String generateJwtToken(User user) {
    System.out.println("Secret_key: " + SECRET_Key);
    return Jwts.builder()
      .subject(user.getEmail())
      .claim("id", user.getId())
      .claim("username", user.getUsername())
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
      .signWith(SECRET_Key)
      .compact();
  }

  public String generateRefreshToken(User user) {
    String token = UUID.randomUUID().toString();
    RefreshTokens refreshTokens = new RefreshTokens(token);
    refreshTokens.setUser(user);
    refreshTokensRepository.save(refreshTokens);
    return token;
  }
}
