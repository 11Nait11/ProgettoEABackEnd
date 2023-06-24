package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(value = {AuditTrailListener.class})

public class Utente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String roles; //separati da ,




    @OneToMany(mappedBy = "venditore", cascade = CascadeType.ALL)
    private List<Prodotto> prodotti;

//    lista delle recensioni ricevute (dove utenteRecensito=this.id)
    @OneToMany(mappedBy = "utenteRecensito",cascade = CascadeType.ALL)
    private List<Recensione> recensioni;

    @OneToMany(mappedBy = "destinatario",cascade = CascadeType.ALL)
    private List<Messaggio> messaggi;

    @OneToMany(mappedBy ="venditore", cascade = CascadeType.ALL)
    private List<Ordine> ordiniVenduti;

    @OneToMany(mappedBy ="compratore", cascade = CascadeType.ALL)
    private List<Ordine> ordiniComprati;




//    usa @toString ed escludi campi
    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", firstName='" + nome + '\'' +
                ", lastName='" + cognome + '\'' +
                ", recensioni='" + recensioni + '\'' +
                '}';
    }
}
