package br.ufrn.umbrella.caronasufrn.configs.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.ufrn.umbrella.caronasufrn.models.constants.Paths;
import br.ufrn.umbrella.caronasufrn.models.constants.Roles;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            auth -> auth
            .requestMatchers(HttpMethod.GET, Paths.GET_PUBLIC_PATHS).permitAll()
            .requestMatchers(HttpMethod.POST, Paths.POST_PUBLIC_PATHS).permitAll()
            .requestMatchers(HttpMethod.GET, Paths.GET_PRIVATE_PATHS).hasAnyAuthority(Roles.ROLES)
            .requestMatchers(HttpMethod.PUT, Paths.PUT_PRIVATE_PATHS).hasAnyAuthority(Roles.ROLES)
            .requestMatchers(HttpMethod.DELETE, Paths.DELETE_PRIVATE_PATHS).hasAnyAuthority(Roles.ROLES)
            .requestMatchers(HttpMethod.POST, Paths.POST_PRIVATE_PATHS).hasAnyAuthority(Roles.ROLES)
            .anyRequest().authenticated()
            )
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
