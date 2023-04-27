package com.example.progettoprova.dto;

import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.entities.Utente;
import jakarta.persistence.*;

public class RecensioneDto {

    private Long id;
    private Long autoreId;
    private Long prodottoId;
    private String commento;
    private int valutazione;
}
