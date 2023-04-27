package com.example.progettoprova.services;

import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.RecensioneDto;
import com.example.progettoprova.entities.Image;
import com.example.progettoprova.entities.Recensione;

import java.util.List;

public interface RecensioneService {
    public List<RecensioneDto> dammiRencesioni();
    public RecensioneDto creaRecensione(RecensioneDto r);
//    public void cancella(Long id);
//    public ProdottoDto aggiorna(Long id, Image i);
}
