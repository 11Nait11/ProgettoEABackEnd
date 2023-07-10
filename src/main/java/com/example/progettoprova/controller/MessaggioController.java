package com.example.progettoprova.controller;


import com.example.progettoprova.dto.MessaggioDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.services.MessaggioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messaggio-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Messaggio", description = "Gestisci le comunicazioni")
public class MessaggioController {

    private final MessaggioService messaggioService;


    @Operation(description = "Restituisce i messaggi di un utente dato il suo ID")
    @ApiResponse(description = "Elenco dei messaggi dell'utente restituito con successo", responseCode = "200")
    @Parameter(name = "idUtente", description = "ID dell'utente", required = true, example = "1")

    @GetMapping("messaggi/utente/{idUtente}")
    public ResponseEntity<List<MessaggioDto>> dammiRecensioniUtenteById(@PathVariable("idUtente") Long id) {
        return ResponseEntity.ok(messaggioService.dammiMessaggiUtenteById(id));
    }

    @Operation(description = "Restituisce tutti i messaggi")
    @ApiResponse(description = "Elenco dei messaggi restituito con successo", responseCode = "200")

    @GetMapping("messaggi")
    public ResponseEntity<List<MessaggioDto>> dammiRecensioni() {
        return ResponseEntity.ok(messaggioService.dammiRecensioni());
    }


    @Operation(description = "Salva un nuovo messaggio")
    @ApiResponse(description = "Messaggio salvato con successo", responseCode = "200")

    @PostMapping("salva")
    public HttpStatus salva(@RequestBody MessaggioDto m) {
        messaggioService.salva(m);
        return HttpStatus.OK;
    }



}