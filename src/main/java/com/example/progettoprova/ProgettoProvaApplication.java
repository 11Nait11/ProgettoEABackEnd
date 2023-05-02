package com.example.progettoprova;

import com.example.progettoprova.dao.*;
import com.example.progettoprova.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@SpringBootApplication
public class ProgettoProvaApplication implements CommandLineRunner {

    @Autowired
    UtenteDao utenteDao;
    @Autowired
    ProdottoDao prodottoDao;
    @Autowired
    ImageDao imageDao;
    @Autowired
    RecensioneDao recensioneDao;
    @Autowired
    MessaggioDao messaggioDao;


    private void creaDb() throws IOException {

        Utente u1=new Utente();
        u1.setNome("Paperino");
        u1.setCognome("Bianchi");
        utenteDao.save(u1);

        Utente u2=new Utente();
        u2.setNome("Archimede");
        u2.setCognome("Rossi");
        utenteDao.save(u2);

        Utente u3=new Utente();
        u3.setNome("ZioPaperone");
        u3.setCognome("Gialli");
        utenteDao.save(u3);


        Prodotto prodotto = new Prodotto();
        prodotto.setNomeProdotto("Maglietta");
        prodotto.setDescrizione("Maglietta in cotone a righe");
        prodotto.setPrezzo(19.99);
        prodotto.setCategoria("Abbigliamento");
        prodotto.setCondizione("Nuovo");
        prodotto.setDisponibilita("Disponibile");
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
        prodotto2.setDisponibilita("Disponibile");;
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
        // prodotto.setDataAggiunta(new Date());
        prodotto3.setVenditore(u2);
        // prodotto.setUbicazione("Roma");
        prodottoDao.save(prodotto3);

        Image i =new Image();
        byte[] immagine= Files.readAllBytes(Path.of("src/main/resources/img/Cattura.PNG"));
        i.setProdotto(prodotto);
        i.setImage(immagine);
        imageDao.save(i);

        Image i2 =new Image();
        byte[] immagine2= Files.readAllBytes(Path.of("src/main/resources/img/al.jpg"));
        i2.setProdotto(prodotto);
        i2.setImage(immagine2);
        imageDao.save(i2);

        Recensione r=new Recensione();
        r.setAutore(u2);
//        r.setUtenteRecensito();
        r.setUtenteRecensito(u1);
        r.setCommento("bella ma non balla");
        r.setValutazione(3);
        recensioneDao.save(r);

        Recensione r2=new Recensione();
        r2.setAutore(u3);
//        r.setUtenteRecensito();
        r2.setUtenteRecensito(u1);
        r2.setCommento("veramente pessimo ");
        r2.setValutazione(1);
        recensioneDao.save(r2);

        Messaggio m=new Messaggio();
        m.setMittente(u1);
        m.setDestinatario(u2);
        m.setTesto("Ciao, ci sei?");
        m.setDataInvio(LocalDateTime.now());
        messaggioDao.save(m);


        //lista vuota caricamento lazy? richiede on demand ?
//        System.out.println("Recensioni"+utenteDao.findById(1L));
        System.out.println("RecensioniQuery"+utenteDao.dammiRecensioni(1L));



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
