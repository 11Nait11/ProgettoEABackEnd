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
    private Utente utente;


    @Override
    public String toString() {
        return "Recensione{" +
                "id=" + id +
                ", utenteRecensione=" + utente.getId() +
                '}';
    }
}
