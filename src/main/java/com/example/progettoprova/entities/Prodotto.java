package com.example.progettoprova.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Prodotto {

    private String type;
    private String color;
    private int price;
}
