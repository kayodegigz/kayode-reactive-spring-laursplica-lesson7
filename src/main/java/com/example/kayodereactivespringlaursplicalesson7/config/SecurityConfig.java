package com.example.kayodereactivespringlaursplicalesson7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created by Kayode.Ogunrinde on 3/18/2023.
 */

@Configuration
public class SecurityConfig {

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("kayode")
                .password(passwordEncoder().encode("12345"))
                .authorities("admin")
                .build();

        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http) {
        return http
                .httpBasic()
                .and()
                .authorizeExchange() // authorizeRequests() in a non reactive app
                .pathMatchers("/demo/**")
                .authenticated()
                .anyExchange()
                .permitAll()
                .and()
                .build();
    }
}
