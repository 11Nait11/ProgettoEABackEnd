package com.example.progettoprova.dto;

import lombok.Data;

@Data
public class ImageDto {

    private Long id;
    private Long prodottoId;
    private byte[] image;
}
