package com.example.progettoprova.entities;

import com.example.progettoprova.config.JpaAuditingConfig;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(value = {AuditTrailListener.class, AuditingEntityListener.class})
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private String nomeProdotto;

//    @Column(nullable = false)
    private String descrizione;

//    @Column(nullable = false)
    private double prezzo;

//    @Column(nullable = false)
    private String categoria;

//    @Column(nullable = false)
    private String condizione;

//    @Column(nullable = false)
    private String disponibilita;


    @ManyToOne(optional = false)
    @JoinColumn(name = "venditore", referencedColumnName = "ID")
    private Utente venditore;

    @OneToMany(mappedBy = "prodotto",cascade = CascadeType.ALL)
    private List<Image> images;




    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", nomeProdotto='" + nomeProdotto + '\'' +
                '}';
    }


    //    @Column(nullable = false)
//    private String ubicazione;
//
//

    @CreatedBy
    private Long createBy;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedBy
    private Long lastModifyBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
