package com.example.progettoprova;

import com.example.progettoprova.dao.*;
import com.example.progettoprova.entities.*;
import com.example.progettoprova.services.ImmagineService;
import com.example.progettoprova.services.ProdottoService;
import com.example.progettoprova.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@SpringBootApplication
public class ProgettoProvaApplication implements CommandLineRunner {

    @Autowired
    UtenteDao utenteDao;
    @Autowired
    UtenteService utenteService;
    @Autowired
    ProdottoDao prodottoDao;
    @Autowired
    ImageDao imageDao;
    @Autowired
    RecensioneDao recensioneDao;
    @Autowired
    MessaggioDao messaggioDao;
    @Autowired
    OrdineDao ordineDao;
    @Autowired
    ImmagineService immagineService;
    @Autowired
    ProdottoService prodottoService;
    @Autowired
    PasswordEncoder passwordEncoder;

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


    }//main

    @Override
    public void run(String... args) throws Exception {
        creaDb();

    }


    private void creaDb() throws IOException {


        Utente u1=new Utente();
        u1.setNome("Vinted");
        u1.setCognome("Bianchi");
        u1.setEmail("admin@email.it");
        u1.setPassword("passwd");
        u1.setRoles("ADMIN");
        utenteService.salva(u1);

        Utente u2=new Utente();
        u2.setNome("archimede");
        u2.setCognome("Rossi");
        u2.setEmail(u2.getNome()+"@email.it");
        u2.setPassword("passwd");;
        utenteService.salva(u2);

        Utente u3=new Utente();
        u3.setNome("paperone");
        u3.setCognome("Gialli");
        u3.setEmail(u3.getNome()+"@email.it");
        u3.setPassword("passwd");
        utenteService.salva(u3);


        Prodotto prodotto = new Prodotto();
        prodotto.setNomeProdotto("Scarpa");
        prodotto.setDescrizione("Bellissima Scarpa");
        prodotto.setPrezzo(9999.99);
        prodotto.setCategoria("Abbigliamento");
        prodotto.setCondizione("Nuovo");
        prodotto.setDisponibilita("Disponibile");
        // prodotto.setDataAggiunta(new Date());
        prodotto.setVenditore(u1);
        // prodotto.setUbicazione("Roma");
        prodottoDao.save(prodotto);

        Image i =new Image();
        byte[] immagine= Files.readAllBytes(Path.of("src/main/resources/img/s1.jpg"));
        i.setProdotto(prodotto);
        i.setImage(immagine);
        imageDao.save(i);


        Prodotto prodotto2 = new Prodotto();
        prodotto2.setNomeProdotto("Bracciale");
        prodotto2.setDescrizione("bellissimo Bracciale");
        prodotto2.setPrezzo(79.99);
        prodotto2.setCategoria("Abbigliamento");
        prodotto2.setCondizione("Nuovo");
        prodotto2.setDisponibilita("Disponibile");;
        // prodotto.setDataAggiunta(new Date());
        prodotto2.setVenditore(u1);
        // prodotto.setUbicazione("Roma");
        prodottoDao.save(prodotto2);

        Image i2 =new Image();
        byte[] immagine2= Files.readAllBytes(Path.of("src/main/resources/img/s2.jpg"));
        i2.setProdotto(prodotto2);
        i2.setImage(immagine2);
        imageDao.save(i2);

        Image i3 =new Image();
        byte[] immagine3= Files.readAllBytes(Path.of("src/main/resources/img/s2-2.jpg"));
        i3.setProdotto(prodotto2);
        i3.setImage(immagine3);
        imageDao.save(i3);

        Prodotto prodotto3 = new Prodotto();
        prodotto3.setNomeProdotto("Orologio");
        prodotto3.setDescrizione("Bellissimo Orologio");
        prodotto3.setPrezzo(59.99);
        prodotto3.setCategoria("Abbigliamento");
        prodotto3.setCondizione("Nuovo");
        prodotto3.setDisponibilita("Disponibile");
        // prodotto.setDataAggiunta(new Date());
        prodotto3.setVenditore(u2);
        // prodotto.setUbicazione("Roma");
        prodottoDao.save(prodotto3);

        Image i4 =new Image();
        byte[] immagine4= Files.readAllBytes(Path.of("src/main/resources/img/j1.jpg"));
        i4.setProdotto(prodotto3);
        i4.setImage(immagine4);
        imageDao.save(i4);

        Prodotto prodotto4 = new Prodotto();
        prodotto4.setNomeProdotto("Vestito");
        prodotto4.setDescrizione("Bellissima Vestito");
        prodotto4.setPrezzo(79.99);
        prodotto4.setCategoria("Abbigliamento");
        prodotto4.setCondizione("Nuovo");
        prodotto4.setDisponibilita("Disponibile");
        // prodotto.setDataAggiunta(new Date());
        prodotto4.setVenditore(u3);
        // prodotto.setUbicazione("Roma");
        prodottoDao.save(prodotto4);

        Image i5 =new Image();
        byte[] immagine5= Files.readAllBytes(Path.of("src/main/resources/img/c1.jpg"));
        i5.setProdotto(prodotto4);
        i5.setImage(immagine5);
        imageDao.save(i5);











//        ProdottoDto pDto=new ProdottoDto();
//        pDto.setNomeProdotto("gino");
//        pDto.setPrezzo(15);
//        pDto.setVenditoreId(1L);
//
//        ImageDto imageDto = new ImageDto();
//        byte[] imageData = new byte[] { 0x00, 0x01, 0x02, 0x03 };
//        imageDto.setImage(imageData);
//        List<ImageDto> images = new ArrayList<>();
//        images.add(imageDto);
//        pDto.setImages(images);
//        prodottoService.salva(pDto);





//        salva img letta dal db
//        List<byte[]> im = imageDao.cercaImagesByIdProdotto(1L);
//        int x=0;
//        for(byte[] d:im){
//            Files.write(Paths.get("image"+x+".png"),d);
//            x++;}

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
        m.setMittente(u2);
        m.setDestinatario(u1);
        m.setTesto("Ciao, ci sei?");
        m.setDataInvio(LocalDateTime.now());
        messaggioDao.save(m);

        Messaggio m3=new Messaggio();
        m3.setMittente(u1);
        m3.setDestinatario(u2);
        m3.setTesto("si ciao , dimmi tutto!!");
        m3.setDataInvio(LocalDateTime.now());
        messaggioDao.save(m3);

        m=new Messaggio();
        m.setMittente(u2);
        m.setDestinatario(u1);
        m.setTesto("niente cosi giusto * !?@#èù");
        m.setDataInvio(LocalDateTime.now());
        messaggioDao.save(m);

        m3=new Messaggio();
        m3.setMittente(u1);
        m3.setDestinatario(u2);
        m3.setTesto("ok........");
        m3.setDataInvio(LocalDateTime.now());
        messaggioDao.save(m3);

        Messaggio m2=new Messaggio();
        m2.setMittente(u3);
        m2.setDestinatario(u2);
        m2.setTesto("Lorem ipsum ??? ");
        m2.setDataInvio(LocalDateTime.now());
        messaggioDao.save(m2);

        Ordine o=new Ordine();
        o.setVenditore(u1);
        o.setCompratore(u2);
        ordineDao.save(o);

        Ordine o1=new Ordine();
        o1.setVenditore(u2);
        o1.setCompratore(u1);
        ordineDao.save(o1);


        //lista vuota caricamento lazy? richiede on demand ?
//        System.out.println("Recensioni"+utenteDao.findById(1L));
//        System.out.println("RecensioniQuery"+utenteDao.dammiRecensioni(1L));
        //System.out.println("listaImg"+prodottoDao.dammiListaImmaginiDiUnProdottoByIdProdotto(1L));
//        System.out.println("OrdineQuery"+utenteDao.dammiOrdiniVenduti(1L));
//        System.out.println("OrdineQuery"+utenteDao.dammiOrdiniComprati(1L));
//        System.out.println("Image"+imageService.dammiImmaginiByIdProdotto(1L));
//        System.out.println(prodottoService.dammiImmaginiByIdProdotto(1L));

    }






}
