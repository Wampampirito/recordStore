package com.recordstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for configuring security settings in the application.
 * <p>
 * This configuration defines security rules for HTTP requests and the `PasswordEncoder` bean used for password encoding.
 * It also customizes the HTTP security by allowing all requests, disabling CSRF protection, and allowing iframe usage (required for H2 console).
 * </p>
 * 
 * Main functionalities:
 * <ul>
 *   <li><b>securityFilterChain(HttpSecurity http)</b>: Configures HTTP security settings, such as request authorization, CSRF disabling, and iframe support.</li>
 *   <li><b>passwordEncoder()</b>: Provides a bean of type {@link PasswordEncoder} using BCrypt for password encoding.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
 * String encodedPassword = passwordEncoder.encode("mySecretPassword");
 * </pre>
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures HTTP security settings.
     * 
     * @param http The HttpSecurity object used to configure web security.
     * @return The configured {@link SecurityFilterChain} object.
     * @throws Exception If any error occurs during security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // TODO: Implement authentication (currently allowing all requests without authentication)
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Allow all routes without authentication
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection to avoid blocks
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // Allow iframe usage (needed for H2 console)

        return http.build();
    }

    /**
     * Provides a bean of {@link PasswordEncoder} for password encoding using BCrypt.
     * 
     * @return The {@link PasswordEncoder} bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
