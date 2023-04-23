package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdottoDao extends JpaRepository<Prodotto,Long> {


}
