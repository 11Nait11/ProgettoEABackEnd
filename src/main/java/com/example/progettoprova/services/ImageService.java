package com.example.progettoprova.services;

import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.entities.Image;

import java.util.List;

public interface ImageService {

    public void salva(Image i);
    public void cancella(Long id);
    public ProdottoDto aggiorna(Long id, Image i);
    public List<ImageDto> dammiImmaginiByIdProdotto(Long id);
    public List<ImageDto> dammiImmagini();

}
