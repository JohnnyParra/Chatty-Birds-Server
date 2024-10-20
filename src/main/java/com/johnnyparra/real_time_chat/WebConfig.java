package com.johnnyparra.real_time_chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/graphql")
      .allowedOrigins("http://localhost:3000") // Allow requests from this origin
      .allowedMethods("GET", "POST") // Allow specific methods
      .allowCredentials(true);

    registry.addMapping("/graphiql")
      .allowedOrigins("http://localhost:3000")
      .allowedMethods("GET", "POST")
      .allowCredentials(true);
  }
}
