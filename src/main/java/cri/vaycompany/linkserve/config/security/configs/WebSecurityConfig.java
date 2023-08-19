package cri.vaycompany.linkserve.config.security.configs;


import cri.vaycompany.linkserve.config.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static cri.vaycompany.linkserve.enums.Role.PROVIDER;
import static cri.vaycompany.linkserve.enums.Role.USER;
import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

        .csrf()
                .disable()
                .authorizeRequests()
                // ... the other matchers are unchanged ...
                .requestMatchers("/api/v1/provider/**").hasRole(PROVIDER.name())
                .requestMatchers(GET, "/api/v1/provider/**").hasRole(PROVIDER.name())
                .requestMatchers(POST, "/api/v1/provider/**").hasRole(PROVIDER.name())
                .requestMatchers(PUT, "/api/v1/provider/**").hasRole(PROVIDER.name())
                .requestMatchers(DELETE, "/api/v1/provider/**").hasRole(PROVIDER.name())
                .requestMatchers("/api/v1/user/**").hasRole(USER.name())
                .requestMatchers(GET, "/api/v1/user/**").hasRole(USER.name())
                .requestMatchers(POST, "/api/v1/user/**").hasRole(USER.name())
                .requestMatchers(PUT, "/api/v1/user/**").hasRole(USER.name())
                .requestMatchers(DELETE, "/api/v1/user/**").hasRole(USER.name())



                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

        return http.build();
    }
}