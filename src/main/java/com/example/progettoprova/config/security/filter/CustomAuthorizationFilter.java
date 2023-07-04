package com.example.progettoprova.config.security.filter;
import com.example.progettoprova.config.security.SecurityConstants;
import com.example.progettoprova.config.security.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
//leggo request per cercare presenza token inviato dall'utente
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        String token = null;
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        logger.info("Authroziation Request URI: " + uri);
        logger.info("Authorization Header : " + authorizationHeader);

        if (authorizationHeader == null || (!authorizationHeader.startsWith(SecurityConstants.BASIC_TOKEN_PREFIX) && !authorizationHeader.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX))) {
//            this.logger.trace("Header Authorization non trovato!");
            log.info("Header Authorization non trovato");
            filterChain.doFilter(request, response);
            return;
        }

        if ((authorizationHeader.startsWith(SecurityConstants.BASIC_TOKEN_PREFIX) )) {
            log.info("Basic ");
            filterChain.doFilter(request, response);

        } else {
            if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX) && !uri.endsWith(SecurityConstants.LOGIN_URI_ENDING)) {
                log.info("entro if : " + SecurityConstants.BEARER_TOKEN_PREFIX);
                try {
                    token = authorizationHeader.substring("Bearer ".length());
                    UsernamePasswordAuthenticationToken authenticationToken = TokenManager.parseToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);

                } catch (ExpiredJwtException e) {
                    log.error(String.format("Token scaduto: %s", token), e);
                    response.setStatus(UNAUTHORIZED.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("errorMessage", "Token scaduto");
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
                catch (Exception e) {
                    log.error(String.format("Errore Token: %s", token), e);
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("errorMessage", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                log.info("No bearer no basic");
                filterChain.doFilter(request, response);
            }
        }
    }
}
