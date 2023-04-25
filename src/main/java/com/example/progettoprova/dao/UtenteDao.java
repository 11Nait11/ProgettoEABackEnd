package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtenteDao extends JpaRepository<Utente,Long> {

//    @Query("select u.annunci from Utente u  where u.firstName=:nome")
//    List<Prodotto> ciccio(@Param("nome") String nome);
//
//    @Query("select u.annunci from Utente  u where u.id=:id")
//    List<Prodotto> ciccio2(@Param("id") Long id);

    //    List<Prodotto> findAllById(Long id);
    @Query("select u.prodotti from Utente u where u.id=:id")
    List<Prodotto> cercaProdottiByIdUtente(@Param("id") Long id);

}

