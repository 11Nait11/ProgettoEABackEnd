package com.example.progettoprova.dto;

import com.example.progettoprova.entities.Image;
import com.example.progettoprova.entities.Utente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class ProdottoDto {
    @JsonIgnore
    private Long id;
    private String nomeProdotto;
    private double prezzo;
    private Long venditoreId;
//    private List<ImageDto> images;

}
