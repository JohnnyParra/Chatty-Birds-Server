package com.johnnyparra.real_time_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnnyparra.real_time_chat.entities.UserPreferences;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {
  // UserPreferences findById(long id);1

}
