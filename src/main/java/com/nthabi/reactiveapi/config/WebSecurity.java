package com.nthabi.reactiveapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebSecurity {

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                               ReactiveAuthenticationManager authenticationManager) {
        return http.authorizeExchange(exchanges ->  exchanges
                .pathMatchers(HttpMethod.POST, "/users/create").permitAll()
                .pathMatchers(HttpMethod.POST, "/users/login").permitAll()
                       .anyExchange().authenticated()
        ).csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authenticationManager(authenticationManager) //tell spring which Auth manager to use
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

}
