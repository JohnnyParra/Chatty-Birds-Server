package com.johnnyparra.real_time_chat.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.johnnyparra.real_time_chat.entities.User;
import com.johnnyparra.real_time_chat.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      return new CustomUserDetails(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) { // users can have the same username
    throw new UnsupportedOperationException("use loadUserById instead");
  }
}
