package com.example.progettoprova.services;

import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.entities.Image;

import java.util.List;

public interface ProdottoService {
    public List<ProdottoDto> dammiProdotti();
    public List<ProdottoDto> dammiProdottiDiUnUtenteById(Long id);
    public ProdottoDto dammiProdottoById(Long id);
    public void salva(ProdottoDto p);
    public List<ProdottoDto> dammiProdottiDiUnUtenteByIdOrdCrescByPrezzo(Long id);

    public void cancella(Long id);
    public ProdottoDto aggiorna(Long id, ProdottoDto utente);

    public List<Image> dammiImmaginiByIdProdotto(Long id);


}
