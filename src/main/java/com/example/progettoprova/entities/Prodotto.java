package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeProdotto;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private double prezzo;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String condizione;

    @Column(nullable = false)
    private String disponibilita;

    @Lob
    private byte[] immagine;


    @ManyToOne(optional = false)
    @JoinColumn(name = "venditore", referencedColumnName = "ID")
    private Utente venditore;

//    @Column(nullable = false)
//    private String ubicazione;
//
//

//    @CreatedBy
//    private Long createBy;
//
//    @CreatedDate
//    private LocalDateTime createDate;
//
//    @LastModifiedBy
//    private Long lastModifyBy;
//
//    @LastModifiedDate
//    private LocalDateTime lastModifiedDate;

}
