package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Image;
import com.example.progettoprova.entities.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecensioneDao extends JpaRepository<Recensione,Long> {


}
