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


    private void creaDb(){

        Utente u1=new Utente();
        u1.setNome("Paperino");
        u1.setCognome("Bianchi");
        utenteDao.save(u1);

        Utente u2=new Utente();
        u2.setNome("Archimede");
        u2.setCognome("Rossi");
        utenteDao.save(u2);

        Prodotto prodotto = new Prodotto();
        prodotto.setNomeProdotto("Maglietta");
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

        Prodotto prodotto2 = new Prodotto();
        prodotto2.setNomeProdotto("Maglietta2");
        prodotto2.setDescrizione("Maglietta in cotone a righe");
        prodotto2.setPrezzo(19.99);
        prodotto2.setCategoria("Abbigliamento");
        prodotto2.setCondizione("Nuovo");
        prodotto2.setDisponibilita("Disponibile");
        prodotto2.setImmagine(new byte[]{1, 2, 3});
        // prodotto.setDataAggiunta(new Date());
        prodotto2.setVenditore(u2);
        // prodotto.setUbicazione("Roma");
        prodottoDao.save(prodotto2);

        Prodotto prodotto3 = new Prodotto();
        prodotto3.setNomeProdotto("Maglietta3");
        prodotto3.setDescrizione("Maglietta in cotone a righe");
        prodotto3.setPrezzo(19.99);
        prodotto3.setCategoria("Abbigliamento");
        prodotto3.setCondizione("Nuovo");
        prodotto3.setDisponibilita("Disponibile");
        prodotto3.setImmagine(new byte[]{1, 2, 3});
        // prodotto.setDataAggiunta(new Date());
        prodotto3.setVenditore(u2);
        // prodotto.setUbicazione("Roma");
        prodottoDao.save(prodotto3);

    }

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



    @Override
    public void run(String... args) throws Exception {
        creaDb();
    }
}
