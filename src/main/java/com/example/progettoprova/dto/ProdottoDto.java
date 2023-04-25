package com.example.progettoprova.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor va inserito ?
public class ProdottoDto {


    private Long id;
    private String nomeProdotto;
    private double prezzo;
}
