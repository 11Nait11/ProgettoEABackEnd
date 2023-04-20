package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Annuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AnnuncioDao extends JpaRepository<Annuncio,Long> {


}
