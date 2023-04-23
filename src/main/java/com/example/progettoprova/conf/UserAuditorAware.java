package com.example.progettoprova.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Random;

public class UserAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        // logica per ricavare l'utente loggato (SpringSecurity)
        //al momento ritorna random
        return Optional.of(new Random().nextLong(3));
    }
//    /* crea bean per @CreateDate personallizato*/
//    @Bean
//    public DateTimeProvider dateTimeProvider() {
//        return () -> Optional.of(LocalDateTime.now(ZoneId.of("GMT+1")));
//    }
}
