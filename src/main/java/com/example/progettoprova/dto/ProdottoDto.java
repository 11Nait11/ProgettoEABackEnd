package com.example.progettoprova.dto;

import com.example.progettoprova.entities.Utente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdottoDto {


    private Long id;
    private String nomeProdotto;
    private double prezzo;
    private Long venditoreId;
}
