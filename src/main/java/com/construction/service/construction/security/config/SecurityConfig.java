package com.construction.service.construction.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for specific endpoints (e.g., H2 Console)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/api/auth/**","/api/auth/login"))

                // Disable frame options to allow H2 console usage
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Public access for login and registration
                        .requestMatchers("/h2-console/**").permitAll() // Public access for H2 console
                        //.requestMatchers("/api/auth/login/**").permitAll() // Public access for H2 console
                        .requestMatchers("/api/services/**").hasRole("ADMIN") // Restrict services to ADMIN role
                        .requestMatchers("/api/clients/**").hasRole("CLIENT") // Restrict clients to CLIENT role
                        .anyRequest().authenticated() // All other requests require authentication
                )

                // Enable HTTP Basic Authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}