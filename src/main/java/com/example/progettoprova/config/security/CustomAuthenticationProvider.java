package com.example.progettoprova.config.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
//@Override metodo authenticate chiamato in CustomAuthenticationFilter per match credenzialiUtente->Db
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AccessDeniedException {
        if (authentication == null)
            return null;

        String uname = String.valueOf(authentication.getName());
        String upassw = String.valueOf(authentication.getCredentials());
        UserDetails userDetails = userDetailsService.loadUserByUsername(uname);

        UsernamePasswordAuthenticationToken authToken = null;
        if (passwordEncoder.matches(upassw, userDetails.getPassword())) {
            authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
