package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Image;
import com.example.progettoprova.entities.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecensioneDao extends JpaRepository<Recensione,Long> {

    @Query("SELECT r FROM Recensione r WHERE r.utenteRecensito.id = :utenteId")
    List<Recensione> findAllByUtenteRecensitoId(@Param("utenteId") Long utenteId);


}
