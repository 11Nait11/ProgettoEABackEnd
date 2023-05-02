package com.example.progettoprova.services;

import com.example.progettoprova.dto.MessaggioDto;
import com.example.progettoprova.entities.Messaggio;

import java.util.List;

public interface MessaggioService {

    public List<MessaggioDto> dammiMessaggiUtenteById(Long id);
    public List<MessaggioDto> dammiRecensioni();
}
