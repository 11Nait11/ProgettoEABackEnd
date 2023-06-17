package com.example.progettoprova.config.security;

import com.example.progettoprova.entities.Utente;
import com.example.progettoprova.services.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UtenteService utenteService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Utente u= utenteService.
        return null;
    }
}
