package com.johnnyparra.real_time_chat.services;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import reactor.core.publisher.Mono;

@Component
public class ResponseHeaderInterceptor implements WebGraphQlInterceptor {

  private final JwtUtility jwtUtility;
  private final CustomUserDetailsService customUserDetailsService;

  public ResponseHeaderInterceptor(JwtUtility jwtUtility, CustomUserDetailsService customUserDetailsService) {
    this.jwtUtility = jwtUtility;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
    MultiValueMap<String, HttpCookie> cookies = request.getCookies();

    String jwt = jwtUtility.getToken(cookies, "jwt");
    String refreshToken = jwtUtility.getToken(cookies, "refreshToken");

    if (jwt == null && jwtUtility.isRefreshTokenValid(refreshToken)) {
      System.out.println("generating refresh token: " +  refreshToken);
      jwt = jwtUtility.generateJwtFromRefreshToken(refreshToken);
    } 
    if (jwt != null && jwtUtility.validateJwtToken(jwt)) {
      authenticateUserFromJwt(jwt, request);
      final String finalJwt = jwt;
      return chain.next(request).doOnNext((response) -> {
        ResponseCookie jwtCookie = ResponseCookie
          .from("jwt", finalJwt)
          .httpOnly(true)
          .secure(true)
          .path("/")
          .maxAge(900)
          .build();
        response.getResponseHeaders().add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
      });
    }

    return chain.next(request).doOnNext((response) -> {
      var context = response.getExecutionInput().getGraphQLContext();
      if (context.hasKey("jwt")) {
        String newJwt = context.get("jwt");
        ResponseCookie jwtCookie = ResponseCookie
          .from("jwt", newJwt)
          .httpOnly(true)
          .secure(true)
          .path("/")
          .maxAge(900)
          .build();
        response.getResponseHeaders().add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
      }

      if (context.hasKey("refreshToken")) {
        String newRefreshToken = context.get("refreshToken");
        ResponseCookie refreshTokenCookie = ResponseCookie
          .from("refreshToken", newRefreshToken)
          .httpOnly(true)
          .secure(true)
          .path("/")
          .maxAge(604800)
          .build();
        response.getResponseHeaders().add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
      }
    });
  }

  private void authenticateUserFromJwt(String jwt, WebGraphQlRequest request) {
    Long userId = jwtUtility.getUserIdFromJwtToken(jwt);
    UserDetails userDetails = customUserDetailsService.loadUserById(userId);

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(request);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

}
