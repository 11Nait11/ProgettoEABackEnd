package com.example.progettoprova;

import com.example.progettoprova.controller.RecensioneController;
import com.example.progettoprova.dao.ProdottoDao;
import com.example.progettoprova.dao.ImageDao;
import com.example.progettoprova.dao.RecensioneDao;
import com.example.progettoprova.dao.UtenteDao;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.RecensioneDto;
import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.services.ImmagineService;
import com.example.progettoprova.services.RecensioneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProgettoProvaApplicationTests {

    @Autowired
    ProdottoDao prodottoDao;
    @Autowired
    UtenteDao utenteDao;
    @Autowired
    ImageDao imageDao;
    @Autowired
    RecensioneDao recensioneDao;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RecensioneService recensioneService;


    @Autowired
    ImmagineService immagineService;


    @Test
    @Disabled
    void contextLoads() throws IOException {

//        Prodotto p1=new Prodotto();
//        p1.setType("Scarpe");
//        p1.setColor("Rosso");
//        p1.setPrice(150);
//
//
//
//
//
//        Utente u1=new Utente();
//        u1.setNome("Paperino");
//        u1.setCognome("Bianchi");
//        utenteDao.save(u1);
//
//        Utente u2=new Utente();
//        u2.setNome("Archimede");
//        u2.setCognome("Rossi");
//        utenteDao.save(u2);
//
//        Recensione r=new Recensione();
//        r.setUtente(u1);
//        recensioneDao.save(r);
//
//        Recensione r2=new Recensione();
//        r2.setUtente(u1);
//        recensioneDao.save(r2);
//
//
//        Prodotto a1=new Prodotto();
//        a1.setTitolo("Scarpe Fantastici");
//        a1.setUtente(u1);
//        a1.setProdotto(p1);
//        prodottoDao.save(a1);
//
//        byte[] immagine= Files.readAllBytes(Path.of("src/main/resources/img/Cattura.PNG"));
//        Image i=new Image();
//        i.setProdotto(a1);
//        i.setImage(immagine);
//        imageDao.save(i);
//
//        Image i2=new Image();
//        i2.setProdotto(a1);
//        i2.setImage(immagine);
//        imageDao.save(i2);


        //recensioneDao.delete(r);

//        annuncioDao.delete(annuncioDao.findById(1L).get());
//        utenteDao.delete(utenteDao.findById(1L).get());
//        recensioneDao.delete(recensioneDao.findById(1L).get());
//        imageDao.delete(i);

//        System.out.println("Tutte Le Recensioni"+recensioneDao.findAll());
//        System.out.println("Tutte gli Utenti"+utenteDao.findAll());
        //System.out.println("Tutte gli Annunci"+ prodottoDao.findAll());
//        System.out.println("Lista Immagini: "+imageDao.findAll());
//        System.out.println("TUTTI UTENTI: "+utenteDao.findAll());
//        System.out.println("TUTTE LE RECENSIONI: "+recensioneDao.findAll());

//        modo corretto per ritornare lista annuncio chiamare query
//        Optional<Utente> recuperoUtente=utenteDao.findById(1L);
//        System.out.println(recuperoUtente.get().getFirstName());
//        System.out.println(utenteDao.ciccio2(1L));

        /**Sorted */
//        System.out.println(utenteDao.findAll(Sort.by("firstName").descending()));
//          decrescente in base al nome, con nomi uguali crescente in base cognome
//        System.out.println(utenteDao.findAll(Sort.by("firstName").descending().and(Sort.by("lastName").ascending())));
//          ordina in base ad un array, sorted3 in serviceProva
//        System.out.println(new ServiceProva().getAllSorted3("firstName","ASC","lastName","ASC",utenteDao));
        /**Paginazione */
//          oggetto page devi chiamare il getContent per stampare
//        System.out.println(new ServiceProva().getPaged(3,utenteDao).getContent());


    }

    @Test
    @Disabled
    void testProdottiVendutiDaUtente() {


        utenteDao.findAll().forEach(u -> System.out.println(u.getNome()));
        System.out.println(utenteDao.cercaProdottiByIdUtente(1L));

    }

    @Test
    @Disabled
    void dammiInfoProdotto() {
        Optional<Prodotto> prodotto = prodottoDao.findById(1L);
        System.out.println("PRODOTTO " + prodotto.get());
        ProdottoDto dto = modelMapper.map(prodotto, ProdottoDto.class);
        System.out.println("PRODOTTODTO " + dto);
    }

    @Test
    @Disabled
    void queryImage() {

        //imageService.dammiImageByIdProdotto(1L);

    }

    @Test
    void recensioneService() {
        System.out.println(recensioneService.dammiRencesioni());

    }

//    @Mock
//    private RecensioneService recensioneServiceMock;
//
//    @InjectMocks
//    private RecensioneController recensioneController;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(recensioneController).build();
//
//    }
//    @Test
//    public void testDammiRecensioni() throws Exception {
//        List<RecensioneDto> recensioni = new ArrayList<>();
//
//        RecensioneDto r1=new RecensioneDto();
//        r1.setId(1L);
//        r1.setCommento("Ottimo prodotto");
//        r1.setValutazione(5);
//        recensioni.add(r1);
//
//        RecensioneDto r2=new RecensioneDto();
//        r2.setId(2L);
//        r2.setCommento("Scarsa qualità");
//        r2.setValutazione(2);
//        recensioni.add(r2);
//
//
//        Mockito.when(recensioneService.dammiRencesioni()).thenReturn(recensioni);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/recensione-api/recensioni"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descrizione").value("Ottimo prodotto"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].voto").value(5))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].descrizione").value("Scarsa qualità"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].voto").value(2));
//    }




}



