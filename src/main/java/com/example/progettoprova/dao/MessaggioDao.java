package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MessaggioDao extends JpaRepository<Messaggio,Long> {


    @Query("from  Messaggio m where m.destinatario.id =:id OR m.mittente.id =:id")
    List<Messaggio> dammiMessaggiUtenteById(@Param("id") Long id);
}
