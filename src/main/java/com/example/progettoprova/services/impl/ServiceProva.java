package com.example.progettoprova.services.impl;

import com.example.progettoprova.dao.UtenteDao;
import com.example.progettoprova.entities.Utente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProva {

//passato direttamente alla funzione. qui risulta null
//    @Autowired
//    UtenteDao utenteDao;


    public List<Utente> getAllSorted3(String colSort1, String Ord1, String colSort2, String Ord2,UtenteDao utenteDao) {
        Utente u1=new Utente();
        u1.setNome("Paperino");
        u1.setCognome("Bianchi");
        utenteDao.save(u1);

//        Ord puo essere  ASC o DESC
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.fromString(Ord1), colSort1));
        orders.add(new Sort.Order(Sort.Direction.fromString(Ord2), colSort2));
        return utenteDao.findAll( Sort.by(orders));
    }


    /** visualizzo due elementi quando ne voglio altri devo fare altra query(solo su richiesta)*/
//    p:da dove parte size:quanti elementi (non resituisce lista ma pagina)
    public Page<Utente> getPaged(int p,UtenteDao utenteDao){
        return utenteDao.findAll(PageRequest.of(p,2));
    }

//    private final  int SIZE_FOR_PAGE=10;
//    public Page<Utente> getAllByLastNameStartWith(String lastName, int page) {
////    la paginazione puo essere ordinata inserendo un terzo parametro sort, e salvo in un oggetto pageRequest
//        PageRequest pageRequest = PageRequest.of(page,SIZE_FOR_PAGE,Sort.by("lastName").ascending());
////    chiamo funzione gia precompilata da springData (findAllByLastName) + like per matchare stringhe parziali
//        return studentDao.findAllByLastNameLike(lastName, pageRequest);
//    }
}
