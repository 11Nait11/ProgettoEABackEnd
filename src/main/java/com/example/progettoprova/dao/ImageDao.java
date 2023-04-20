package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Annuncio;
import com.example.progettoprova.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageDao extends JpaRepository<Image,Long> {


}
