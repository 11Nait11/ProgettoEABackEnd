package com.example.progettoprova.conf;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching//sara' memorizzata in cache per future richieste
@EnableScheduling
public class CacheConfig {
    public static final String CACHE_FOR_UTENTE="UTENTE";//nome cache per oggetti utente

    @Bean//salve istanza di CacheManager nel context, per recuperarlo vedi main(usa get bean dal context)
    public CacheManager manager(){
        return new ConcurrentMapCacheManager(CACHE_FOR_UTENTE);//inserisce una cache(mappa) nel Contenitore delle cache
    }

//    @CacheEvict(allEntries = true,value = {CACHE_FOR_UTENTE})
//    @Scheduled(fixedDelay = 4000,initialDelay = 2500)
//    public void cacheEvictStudent(){
//        System.out.println("Cache Svuotata");
////        qui potresti chiamare la funzione che ricarica in cache
//    }
}
