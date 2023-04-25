package com.example.progettoprova.services;

import com.example.progettoprova.dao.ProdottoDao;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.entities.Prodotto;

import java.util.List;

public interface ProdottoService {
    public List<ProdottoDto> dammiProdotti();
    public List<ProdottoDto> dammiProdottiDiUnUtenteById(Long id);
    public void salva(ProdottoDto p);

    public void cancella(Long id);
    public ProdottoDto aggiorna(Long id, ProdottoDto utente);
}
