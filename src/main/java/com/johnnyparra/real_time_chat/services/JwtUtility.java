package com.johnnyparra.real_time_chat.services;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.johnnyparra.real_time_chat.entities.RefreshTokens;
import com.johnnyparra.real_time_chat.entities.User;
import com.johnnyparra.real_time_chat.repositories.RefreshTokensRepository;
import com.johnnyparra.real_time_chat.repositories.UserRepository;

@Component
public class JwtUtility {
  private final String jwtSecretKey = ""; // place holder
  private static final long JWT_EXPIRATION_TIME = 900000; // 15 minutes
  private final RefreshTokensRepository refreshTokensRepository;

  public JwtUtility(RefreshTokensRepository refreshTokensRepository, UserRepository userRepository) {
    this.refreshTokensRepository = refreshTokensRepository;
  }

  public String generateJwtToken(User user) {
    SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecretKey));
    return Jwts.builder()
      .subject(user.getEmail())
      .claim("id", user.getId())
      .claim("username", user.getUsername())
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
      .signWith(secretKey)
      .compact();
  }

  public String generateRefreshToken(User user) {
    String token = UUID.randomUUID().toString();
    RefreshTokens refreshTokens = new RefreshTokens(token);
    refreshTokens.setUser(user);
    refreshTokensRepository.save(refreshTokens);
    return token;
  }

  public String generateJwtFromRefreshToken(String refreshToken) {
    if (!isRefreshTokenValid(refreshToken)) {
      throw new RuntimeException("Invalid refresh token");
    }

    RefreshTokens newRefreshToken = refreshTokensRepository.findByRefreshToken(refreshToken);
    User user = newRefreshToken.getUser();

    return generateJwtToken(user);
  }

  public String getToken(MultiValueMap<String, HttpCookie> cookies, String cookieName) {
    try {
      if (cookies.containsKey(cookieName)) {
        return cookies.getFirst(cookieName).getValue();
      }
    } catch (Exception e) {
      throw new RuntimeException("Cannot get JWT token", e);
    }

    return null;
  }

  public boolean isRefreshTokenValid(String token) {
    RefreshTokens refreshToken = refreshTokensRepository.findByRefreshToken(token);

    if (refreshToken == null) {
      return false;
    }

    if (refreshToken.getExpiresAt().before(new Date())) {
      refreshTokensRepository.deleteByRefreshToken(token);
      return false;
    }

    return true;
  }

  // public String getRefreshToken(HttpServletRequest request) {
  //   Cookie[] cookies = request.getCookies();
  //   if (cookies != null) {
  //     for (Cookie cookie : cookies) {
  //       if (cookie.getName().equals("refreshToken")) {
  //         return cookie.getValue();
  //       }
  //     }
  //   }

  //   return null;
  // }

  public Long getUserIdFromJwtToken(String token) {
    SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecretKey));

    try {
      var claims = Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();

        Long userId = claims.get("id", Long.class);
        Date expirationDate = claims.getExpiration();
        Long now = System.currentTimeMillis();

        if (expirationDate == null || expirationDate.getTime() < now) {
          throw new RuntimeException("Invalid JWT token");
        }

        return userId;
    } catch(Exception e) {
      throw new RuntimeException("Invalid JWT token", e);
    }
  }

  public boolean validateJwtToken(String token) {
    SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecretKey));

    try {
      Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public void deleteRefreshToken(String token) {
    refreshTokensRepository.deleteByRefreshToken(token);
  }
}
