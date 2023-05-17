package com.example.progettoprova.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.Random;

public class UserAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        // logica per ricavare l'utente loggato (SpringSecurity)
        //al momento ritorna random e va a settare campo createdBy di prodotto
        //in realta' di tutte le entita che usano @createdBy?
        return Optional.of(new Random().nextLong(3));
    }
//    /* crea bean per @CreateDate personallizato*/
//    @Bean
//    public DateTimeProvider dateTimeProvider() {
//        return () -> Optional.of(LocalDateTime.now(ZoneId.of("GMT+1")));
//    }
}
