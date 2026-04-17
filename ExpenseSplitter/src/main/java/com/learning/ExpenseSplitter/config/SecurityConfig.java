package com.learning.ExpenseSplitter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//1. Disable CSRF
//2. Allow /login (public)
//3. Protect others
//4. Add JwtFilter
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf-> csrf.disable());
        http.authorizeHttpRequests(auth ->auth
                        .requestMatchers("/login","/users").permitAll()
                        .anyRequest().authenticated()
        );

        http.sessionManagement(session ->
              session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}