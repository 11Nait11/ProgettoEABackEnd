package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(name = "ANNUNCIO_ID", referencedColumnName = "ID")
    public Annuncio annuncio;

    @Lob
    public byte[] image;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", annuncio=" + annuncio.getTitolo() +
                ", image="+
                '}';
    }
}
