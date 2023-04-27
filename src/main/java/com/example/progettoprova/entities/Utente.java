package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Utente {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String cognome;

    @OneToMany(mappedBy = "venditore", cascade = CascadeType.ALL)
    private List<Prodotto> prodotti;

    @OneToMany(mappedBy = "utenteRecensito",cascade = CascadeType.ALL)
    private List<Recensione> recensioni= new ArrayList<>();

//    usa @toString ed escludi campi
    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", firstName='" + nome + '\'' +
                ", lastName='" + cognome + '\'' +
                '}';
    }
}
