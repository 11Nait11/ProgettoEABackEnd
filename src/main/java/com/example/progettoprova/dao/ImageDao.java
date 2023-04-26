package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ImageDao extends JpaRepository<Image,Long> {


    @Query("select i from Image  i where i.prodotto.id=:idProdotto")
    List<Image>cercaImagesByIdProdotto(@Param("idProdotto") Long idProdotto);
}
