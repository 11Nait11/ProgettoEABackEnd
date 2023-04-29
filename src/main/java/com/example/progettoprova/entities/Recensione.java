package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Data
@EntityListeners(value = {AuditTrailListener.class})
@Table(name = "Recensione", uniqueConstraints = @UniqueConstraint(columnNames = {"autore_id","utente_recensito_id"}))
public class Recensione {
    @Id
    @GeneratedValue
    private Long id;
    private String commento;
    private int valutazione;

    @ManyToOne
    @JoinColumn(name = "autore_id", nullable = false)
    private Utente autore;

    @ManyToOne
    @JoinColumn(name = "utente_recensito_id")
    private Utente utenteRecensito;


//    @ManyToOne
//    @JoinColumn(name = "prodotto_id", nullable = false)
//    private Prodotto prodotto;


    @Override
    public String toString() {
        return "Recensione{" +
                "id=" + id +
                ", commento='" + commento + '\'' +
                ", valutazione=" + valutazione +
                ", autoreId=" + autore.getId() +
                ", utenteRecensitoId=" + utenteRecensito.getId() +
                '}';
    }
}
