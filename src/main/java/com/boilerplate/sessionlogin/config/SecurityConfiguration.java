package com.boilerplate.sessionlogin.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(CsrfConfigurer -> CsrfConfigurer.disable())
            .headers((headerConfig) -> headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
            .authorizeHttpRequests(
                (authorizeRequests) -> authorizeRequests
                    .requestMatchers("/api/v1/login", "/api/v1/join").permitAll()
//                .requestMatchers("/api/v1/users").hasRole()
//                .requestMatchers("/api/v1/admins").hasRole()
            );

        return http.build();
    }
}
