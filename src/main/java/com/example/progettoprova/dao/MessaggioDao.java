package com.example.progettoprova.dao;

import com.example.progettoprova.entities.Messaggio;
import com.example.progettoprova.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MessaggioDao extends JpaRepository<Messaggio,Long> {


    @Query("from  Messaggio m where m.destinatario.id =:id OR m.mittente.id =:id")
    List<Messaggio> dammiMessaggiUtenteById(@Param("id") Long id);


    @Query("SELECT m FROM Messaggio m WHERE m.id IN (" +
            "SELECT MAX(m2.id) FROM Messaggio m2 " +
            "WHERE (m2.destinatario.id = :id OR m2.mittente.id = :id) " +
            "GROUP BY CASE " +
            "   WHEN m2.destinatario.id = :id THEN m2.mittente.id " +
            "   ELSE m2.destinatario.id " +
            "END)")
    List<Utente> findContattiByIdUtente(@Param("id") Long id);

}
