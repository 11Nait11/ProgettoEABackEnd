package com.example.progettoprova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProdotto")
    private Prodotto prodotto;

    @Lob
    private byte[] image;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", prodotto=" + prodotto.getId() +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
