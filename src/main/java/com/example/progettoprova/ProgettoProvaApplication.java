package com.example.progettoprova;

import com.example.progettoprova.conf.CacheConfig;
import com.example.progettoprova.entities.Utente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProgettoProvaApplication {

    public static void main(String[] args) {

        //SpringApplication.run(ProgettoProvaApplication.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(ProgettoProvaApplication.class,args);

        /**Cache */
    //        recupera dal context il bean di tipo CacheManger
    //        se hai due bean di tipo CacheManger -> context.getBean("nomeBean", CacheManager.class)
            CacheManager cm=context.getBean(CacheManager.class);
            Utente u1=new Utente();
            u1.setFirstName("gianni");
            u1.setLastName("pino");
    //        salvo oggetto in cache e poi lo recupero
            cm.getCache(CacheConfig.CACHE_FOR_UTENTE).put("gianni",u1);
            System.out.println(cm.getCache(CacheConfig.CACHE_FOR_UTENTE).get("gianni").get());

        /**Sfrattare Cache*/
//            context.getBean(CacheConfig.class).cacheEvictStudent();
//            System.out.println(cm.getCache(CacheConfig.CACHE_FOR_UTENTE).get("gianni").get());
    }

}
