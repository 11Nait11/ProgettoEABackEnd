package com.example.progettoprova.config.security;

import com.example.progettoprova.entities.Utente;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


//@Data // da rivedere
//public class UserDetailsImpl implements UserDetails {
//
//
//    private String username;
////    private String password;
//    private List<GrantedAuthority> auths;
//
////    public UserDetailsImpl(Utente utente) {
////        this.username = utente.getUsername();
////        this.password = utente.getPassword();
////        this.auths = Arrays.stream(utente.getRoles().split(","))
////                .map(r->"ROLE_"+r)
////                .map(SimpleGrantedAuthority::new)
////                .collect(Collectors.toList());
////    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return auths;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
