package com.example.progettoprova.dao;

import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProdottoDao extends JpaRepository<Prodotto,Long> {


//    List<Prodotto> findAllByVenditore(Long id);

    @Query("SELECT p FROM Prodotto p WHERE p.venditore.id = :id")
    List<Prodotto> findAllByVenditoreId(@Param("id") Long id);



}
