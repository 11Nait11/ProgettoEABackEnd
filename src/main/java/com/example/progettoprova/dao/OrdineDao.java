package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Ordine;
import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.entities.Recensione;
import com.example.progettoprova.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdineDao extends JpaRepository<Ordine,Long> {



}

