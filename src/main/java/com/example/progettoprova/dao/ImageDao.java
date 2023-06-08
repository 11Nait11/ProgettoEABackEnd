package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ImageDao extends JpaRepository<Image,Long> {


    @Query(value = "select lo_get(i.image) from Image i where i.id = :idProdotto")
    List<byte[]> cercaImagesByIdProdotto(@Param("idProdotto") Long idProdotto);

    @Query("from Image i where i.id = :idProdotto")
    List<Image> dammiImmaginiByIdProdotto(@Param("idProdotto") Long idProdotto);


    @Query(
            value = "SELECT * from image i where i.id = (select MIN(id) from image WHERE id_prodotto = i.id_prodotto)",
            nativeQuery = true)
    List<Image> dammiUnaImagePerProdotto();






}
