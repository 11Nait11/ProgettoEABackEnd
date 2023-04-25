package com.example.progettoprova.services;


import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Utente;

import java.util.List;

public interface UtenteService {


    public List<UtenteDto> dammiUtenti();
    public List<ProdottoDto> dammiProdottiUtente(Long id);
    public UtenteDto dammiUtente(Long id);
    public void salva(Utente u);
    public void salvaDto(UtenteDto uD);
    public void cancella(Long id);
    public UtenteDto aggiorna(Long id, UtenteDto utente);

}
