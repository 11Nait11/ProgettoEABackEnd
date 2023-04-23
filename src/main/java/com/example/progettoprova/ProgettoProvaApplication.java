package com.example.progettoprova;

import com.example.progettoprova.dao.ProdottoDao;
import com.example.progettoprova.dao.UtenteDao;
import com.example.progettoprova.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ProgettoProvaApplication implements CommandLineRunner {

    @Autowired
    UtenteDao utenteDao;
    @Autowired
    ProdottoDao prodottoDao;

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

        Prodotto prodotto = new Prodotto();
        prodotto.setNome("Maglietta");
        prodotto.setDescrizione("Maglietta in cotone a righe");
        prodotto.setPrezzo(19.99);
        prodotto.setCategoria("Abbigliamento");
        prodotto.setCondizione("Nuovo");
        prodotto.setDisponibilita("Disponibile");
        prodotto.setImmagine(new byte[]{1, 2, 3});
       // prodotto.setDataAggiunta(new Date());
        prodotto.setVenditore(u1);
       // prodotto.setUbicazione("Roma");
        prodottoDao.save(prodotto);

    }

    @Override
    public void run(String... args) throws Exception {
        creaDb();
    }
}
