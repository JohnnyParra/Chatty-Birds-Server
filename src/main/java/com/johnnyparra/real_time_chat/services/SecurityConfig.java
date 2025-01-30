package com.johnnyparra.real_time_chat.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.security.core.userdetails.User;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  // private final JwtUtility jwtUtility;
  // private final CustomUserDetailsService customUserDetailsService;

  // public SecurityConfig(JwtUtility jwtUtility, CustomUserDetailsService customUserDetailsService) {
  //   this.jwtUtility = jwtUtility;
  //   this.customUserDetailsService = customUserDetailsService;
  // }

  @Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user")
      .password(passwordEncoder().encode("password"))
      .roles("USER")
      .build());
		return manager;
	}

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/graphiql").permitAll()
        .requestMatchers("/graphql").permitAll()
        .anyRequest().authenticated()
      )
      // .addFilterBefore(new JwtAuthenticationFilter(jwtUtility, customUserDetailsService),
      //   UsernamePasswordAuthenticationFilter.class)
      .headers(headers -> headers
        .httpStrictTransportSecurity(hsts -> hsts
          .includeSubDomains(true))
      )
      // .requiresChannel(channel -> channel.anyRequest().requiresSecure())
      .csrf(csrf -> csrf.disable()) // fix this for production
      .formLogin(withDefaults())
      .httpBasic(httpBasic -> httpBasic.disable());
      // .cors(cors -> cors.configurationSource(corsConfigurationSource()));
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    int strength = 8;
    return new BCryptPasswordEncoder(strength);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Add allowed origins
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS")); // Allow specific methods
    configuration.setAllowCredentials(true);
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
