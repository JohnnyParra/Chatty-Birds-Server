package com.johnnyparra.real_time_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnnyparra.real_time_chat.entities.RefreshTokens;

@Repository
public interface RefreshTokensRepository extends JpaRepository<RefreshTokens, Long> {
  RefreshTokens findByRefreshToken(String token);
  void deleteByRefreshToken(String token);
}
