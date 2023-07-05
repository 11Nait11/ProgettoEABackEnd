package com.example.progettoprova.dto;

import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.entities.Utente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

@Data
@NoArgsConstructor
public class RecensioneDto {


    @JsonIgnore
    private Long id;
    private Long autoreId;
    private Long utenteRecensitoId;
    @NotBlank
    private String commento;
    @Positive
    private int valutazione;
}
