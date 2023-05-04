package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Ordine {
    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "id_venditore")
    Utente venditore;

    @ManyToOne
    @JoinColumn(name = "id_compratore")
    Utente compratore;

    @Override
    public String toString() {
        return "Ordine{" +
                "id=" + id +
                ", venditore=" + venditore.getId() +
                ", compratore=" + compratore.getId() +
                '}';
    }
}
