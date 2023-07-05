package com.example.progettoprova.dto;

import com.example.progettoprova.entities.Utente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessaggioDto {

    private Long id;
    private String oggetto; //?
    @NotBlank
    private String testo;
    private LocalDateTime dataInvio;
    private String mittenteNome;
    private String destinatarioNome;
    private Long mittenteId;
    private Long destinatarioId;
}
