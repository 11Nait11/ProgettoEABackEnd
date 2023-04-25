package com.example.progettoprova.services;

import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Utente;

import java.util.List;

public interface ProdottoService {
    public List<ProdottoDto> dammiProdotti();
    public List<ProdottoDto> dammiProdottiVenditore(Long id);
    public void salva(ProdottoDto u);
    public void salvaDto(ProdottoDto uD);
    public void cancella(Long id);
    public ProdottoDto aggiorna(Long id, ProdottoDto utente);
}
