package com.johnnyparra.real_time_chat.services;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class ResponseHeaderInterceptor implements WebGraphQlInterceptor {

  @Override
  public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
    return chain.next(request).doOnNext((response) -> {
      var context = response.getExecutionInput().getGraphQLContext();
      if (context.hasKey("jwt")) {
        String jwt = context.get("jwt");
        ResponseCookie jwtCookie = ResponseCookie
          .from("jwt", jwt)
          .httpOnly(true)
          .secure(true)
          .path("/")
          .maxAge(900)
          .build();
        response.getResponseHeaders().add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
      }

      if (context.hasKey("refreshToken")) {
        String refreshToken = context.get("refreshToken");
        ResponseCookie refreshTokenCookie = ResponseCookie
          .from("refreshToken", refreshToken)
          .httpOnly(true)
          .secure(true)
          .path("/")
          .maxAge(604800)
          .build();
        response.getResponseHeaders().add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
      }
    });
  }

}
