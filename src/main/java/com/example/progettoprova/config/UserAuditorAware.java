package com.example.progettoprova.config;

import com.example.progettoprova.config.security.UserDetailsImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Optional;
;

public class UserAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return Optional.of(Long.parseLong(userDetails.getId().toString()));
        }

        return Optional.empty();
    }
//    /* crea bean per @CreateDate personallizato*/
//    @Bean
//    public DateTimeProvider dateTimeProvider() {
//        return () -> Optional.of(LocalDateTime.now(ZoneId.of("GMT+1")));
//    }
}
