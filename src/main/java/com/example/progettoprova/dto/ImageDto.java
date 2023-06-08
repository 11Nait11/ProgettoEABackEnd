package com.example.progettoprova.dto;

import com.example.progettoprova.entities.Prodotto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ImageDto {

    private Long id;
    private byte[] image;
    private Long prodottoId;

}
