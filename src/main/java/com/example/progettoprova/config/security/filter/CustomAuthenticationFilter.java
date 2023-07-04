package com.example.progettoprova.config.security.filter;


import com.example.progettoprova.config.security.SecurityConstants;
import com.example.progettoprova.config.security.TokenManager;
import com.example.progettoprova.config.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
//check su crediaziali inviate dall'utente tramite form, se presenti nel db rilascio token
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String BAD_CREDENTIAL_MESSAGE = "Authentication failed for username: %s and password: %s";

    private final AuthenticationManager authenticationManager;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        log.info("Authentication");
            String username = null;
            String password = null;
            try {//prende credenziali che ha inserito utente e le confronta con il db
                String authorizationHeader = request.getHeader(AUTHORIZATION);
                String headerToken = StringUtils.delete(authorizationHeader, SecurityConstants.BASIC_TOKEN_PREFIX).trim();
                username = TokenManager.decodedBase64(headerToken)[0];
                password = TokenManager.decodedBase64(headerToken)[1];

            this.logger.trace(LogMessage.format("Credentials username '%s' and password '&s' have been found in Basic Authorization header", username, password));

            //chiamo il mio AuthProvider verifica presenza utente nel db
            Authentication authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            logger.info("attemptAuthentication " + authResult.toString());
            return authResult;
        }
        catch (AuthenticationException e) {
            log.error(String.format(BAD_CREDENTIAL_MESSAGE, username, password), e);
            throw e;
        }
        catch (Exception e) {
            response.setStatus(INTERNAL_SERVER_ERROR.value());
            Map<String, String> error = new HashMap<>();
            error.put("errorMessage", e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
            throw new RuntimeException(String.format("Utente non trovato username %s and password %s", username, password), e);
        }
    }

    @Override//se utente presente nel db assegna token
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        log.info("Success ");
        UserDetailsImpl user = (UserDetailsImpl)authentication.getPrincipal();
        log.info("Success user "+user);

        String accessToken = TokenManager.createAccessToken(
                    user.getUsername(),
                    request.getRequestURL().toString(),
                    user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                    user.getId()
                );
        String refreshToken = TokenManager.createRefreshToken(
                user.getUsername()
        );

        response.addHeader("access_token", accessToken);
        response.addHeader("refresh_token", refreshToken);
        log.info("access_token: "+accessToken);
        log.info("refresh_token: "+refreshToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("Unsuccess");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", "Bad credentials");
        response.setContentType(APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), error);
    }
}
