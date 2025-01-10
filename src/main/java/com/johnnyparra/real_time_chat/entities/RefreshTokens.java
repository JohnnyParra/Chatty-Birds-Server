package com.johnnyparra.real_time_chat.entities;

import java.sql.Date;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "refresh_tokens")
@DynamicUpdate
public class RefreshTokens {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  @Column(name = "expires_at", nullable = false)
  private Date expiresAt;

  @Column(name = "token", nullable = false)
  private String refreshToken;

  protected RefreshTokens() {}

  public RefreshTokens(String refreshToken) {
    this.refreshToken = refreshToken;
    this.expiresAt = new Date(System.currentTimeMillis() + 604800000L); // Set expiration to 7 days

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

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
