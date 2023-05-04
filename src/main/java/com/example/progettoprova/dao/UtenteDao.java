package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Ordine;
import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.entities.Recensione;
import com.example.progettoprova.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtenteDao extends JpaRepository<Utente,Long> {


//    ritorna lista dei prodotti di un singolo utente
    @Query("select u.prodotti from Utente u where u.id=:id")
    List<Prodotto> cercaProdottiByIdUtente(@Param("id") Long id);

    @Query("select u.recensioni from Utente u where u.id=:id")
    List<Recensione> dammiRecensioni(@Param("id") Long id);

    @Query("select u.ordiniVenduti from Utente u where u.id=:id")
    List<Ordine> dammiOrdiniVenduti(@Param("id") Long id);

    @Query("select u.ordiniComprati from Utente u where u.id=:id")
    List<Ordine> dammiOrdiniComprati(@Param("id") Long id);

}

