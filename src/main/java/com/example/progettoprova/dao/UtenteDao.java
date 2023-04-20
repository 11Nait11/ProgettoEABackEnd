package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Annuncio;
import com.example.progettoprova.entities.Utente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtenteDao extends JpaRepository<Utente,Long> {

    @Query("select u.annunci from Utente u  where u.firstName=:nome")
    List<Annuncio> ciccio(@Param("nome") String nome);

    @Query("select u.annunci from Utente  u where u.id=:id")
    List<Annuncio> ciccio2(@Param("id") Long id);

}
