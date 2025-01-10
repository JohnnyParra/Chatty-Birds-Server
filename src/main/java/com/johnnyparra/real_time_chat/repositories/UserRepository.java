package com.johnnyparra.real_time_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnnyparra.real_time_chat.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // User findById(long id);
  User findByEmail(String email);
}
