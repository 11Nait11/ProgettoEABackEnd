package com.example.progettoprova.config.security;

import com.example.progettoprova.dto.UtenteDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private List<GrantedAuthority> auths;


    public UserDetailsImpl(UtenteDto utente) {
        this.id=utente.getId();
        this.username = utente.getEmail();
        this.password = utente.getPassword();
        this.auths = Arrays.stream(utente.getRoles().split(","))
                .map(r->"ROLE_"+r)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }



    public Long getId() {
        return id;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auths;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {return username;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
