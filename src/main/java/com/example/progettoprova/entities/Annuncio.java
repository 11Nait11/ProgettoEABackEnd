package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public class Annuncio {
    @Id
    @GeneratedValue
    private Long id;

    private String titolo;

    @Embedded
    private Prodotto prodotto;

    @CreatedBy
    private Long createBy;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedBy
    private Long lastModifyBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UTENTE_ID", nullable = false)
    private Utente utente=new Utente();

    @OneToMany(mappedBy = "annuncio",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Image> images=new ArrayList<>();






    @Override
    public String toString() {
        return "Annuncio{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                  "CreatedBy: "+createBy+ '\'' +
                  "LocalDate: "+createDate+ '\'' +
                  "lastModifiedDate: "+lastModifiedDate+ '\'' +
                  "lastModifyBy: "+lastModifyBy+
                '}';
    }
}
