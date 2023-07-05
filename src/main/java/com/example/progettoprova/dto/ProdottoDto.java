package com.example.progettoprova.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

public class ProdottoDto {

//    @JsonIgnore
    private Long id;
    @NotBlank
    private String nomeProdotto;
    @Positive
    private double prezzo;
    private Long venditoreId;
    private String venditoreNome;
    private List<ImageDto> images;


}
