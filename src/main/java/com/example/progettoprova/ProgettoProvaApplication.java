package com.example.progettoprova;

import com.example.progettoprova.conf.CacheConfig;
import com.example.progettoprova.dao.AnnuncioDao;
import com.example.progettoprova.dao.ImageDao;
import com.example.progettoprova.dao.RecensioneDao;
import com.example.progettoprova.dao.UtenteDao;
import com.example.progettoprova.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class ProgettoProvaApplication implements CommandLineRunner {

    @Autowired
    UtenteDao utenteDao;

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ProgettoProvaApplication.class, args);


////        ConfigurableApplicationContext context = SpringApplication.run(ProgettoProvaApplication.class,args);
////
//        /**Cache */
//    //        recupera dal context il bean di tipo CacheManger
//    //        se hai due bean di tipo CacheManger -> context.getBean("nomeBean", CacheManager.class)
//            CacheManager cm=context.getBean(CacheManager.class);
//            Utente u1=new Utente();
//            u1.setFirstName("gianni");
//            u1.setLastName("pino");
//    //        salvo oggetto in cache e poi lo recupero
//            cm.getCache(CacheConfig.CACHE_FOR_UTENTE).put("gianni",u1);
//            System.out.println(cm.getCache(CacheConfig.CACHE_FOR_UTENTE).get("gianni").get());
//
//        /**Sfrattare Cache*/
////            context.getBean(CacheConfig.class).cacheEvictStudent();
////            System.out.println(cm.getCache(CacheConfig.CACHE_FOR_UTENTE).get("gianni").get());




    }

    private void creaDb(){
        Utente u1=new Utente();
        u1.setFirstName("Paperino");
        u1.setLastName("Bianchi");
        utenteDao.save(u1);

        Utente u2=new Utente();
        u2.setFirstName("Archimede");
        u2.setLastName("Rossi");
        utenteDao.save(u2);
    }

    @Override
    public void run(String... args) throws Exception {
        creaDb();
    }
}
