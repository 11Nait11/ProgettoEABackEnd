package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Recensione {
    @Id
    @GeneratedValue
    private Long id;
    private String commento;
    private int valutazione;

    @ManyToOne(fetch = FetchType.LAZY)
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
                ", utenteRecensione=" + autore.getId() +
                '}';
    }
}
