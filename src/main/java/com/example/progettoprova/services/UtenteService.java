package com.example.progettoprova.services;


import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Utente;

import java.util.List;

public interface UtenteService {
//    creazione di un nuovo utente

//    aggiornamento delle informazioni di un utente
//    eliminazione di un utente


    public List<UtenteDto> dammiUtenti();
    public UtenteDto dammiUtente(Long id);
    public void salva(Utente u);
    public void salvaDto(UtenteDto uD);
    public void cancella();
}
