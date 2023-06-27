package com.example.progettoprova.config.security;


import com.example.progettoprova.config.security.filter.CustomAuthenticationFilter;
import com.example.progettoprova.config.security.filter.CustomAuthorizationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

  

    @Bean//chiamato una sola volta nelle impostazioni di partenza
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        log.info("filtroChain");
        http
            .csrf().disable()
//            .requiresChannel(channel ->
//                channel.anyRequest().requiresSecure())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint((request, response, authEx) -> {
                response.setHeader("WWW-Authenticate", "Basic realm=\"Access to /login authentication endpoint\"");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{ \"Error\": \"" + authEx.getMessage() + " - You are not authenticated.\" }");
            })
            .and()
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/prodotto-api/prodotti").permitAll()
                .requestMatchers(HttpMethod.GET, "/refreshToken").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
            )

                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/prodotto-api/prodotti").hasRole("ADMIN")
//                        .requestMatchers("/utente-api/utente/{username}").access()
                        .anyRequest().authenticated()
                )

            .addFilter(new CustomAuthenticationFilter(authenticationManager))
            .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            .headers().cacheControl();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
