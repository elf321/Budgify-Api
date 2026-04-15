package com.example.Budgify_Api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF'yi kapat (Postman için şart)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // BÜTÜN kapıları aç!
                );
        return http.build();
    }
}