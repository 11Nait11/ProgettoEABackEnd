package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Recensione {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente autore;


    @ManyToOne
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto prodotto;

    private String commento;
    private int valutazione;



    @Override
    public String toString() {
        return "Recensione{" +
                "id=" + id +
                ", utenteRecensione=" + autore.getId() +
                '}';
    }
}
