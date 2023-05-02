package com.example.progettoprova.controller;


import com.example.progettoprova.dto.MessaggioDto;
import com.example.progettoprova.services.MessaggioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messaggio-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MessaggioController {

    private final MessaggioService messaggioService;


    @GetMapping("messaggi/utente/{idUtente}")
    public ResponseEntity<List<MessaggioDto>> dammiRecensioniUtenteById(@PathVariable("idUtente") Long id){
        return ResponseEntity.ok(messaggioService.dammiMessaggiUtenteById(id));
    }

    @GetMapping("messaggi")
    public ResponseEntity<List<MessaggioDto>> dammiRecensioni() {
        return ResponseEntity.ok(messaggioService.dammiRecensioni());
    }


}
