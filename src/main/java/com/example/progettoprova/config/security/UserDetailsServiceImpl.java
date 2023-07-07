package com.example.progettoprova.config.security;

import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.services.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UtenteService utenteService;

    @Override//wrap utente in userDetails
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtenteDto utente=utenteService.dammiUtenteByUsername(username);
        UserDetailsImpl userDetails = new UserDetailsImpl(utente);
        return userDetails;
    }

    @Autowired
    public void setUserService(UtenteService utenteService) {
        this.utenteService = utenteService;
    }
}
