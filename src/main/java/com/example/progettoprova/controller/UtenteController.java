package com.example.progettoprova.controller;

import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Utente;
import com.example.progettoprova.services.UtenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/utente-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteService utenteService;

    @GetMapping("utenti")
    public List<UtenteDto> dammiUtenti(){
        return utenteService.dammiUtenti();
    }

    @GetMapping("utenti/{idUtente}")
    public ResponseEntity<UtenteDto> dammiUtente(@PathVariable Long idUtente){
        System.out.println("ControllerDammiUtente");
        return ResponseEntity.ok(utenteService.dammiUtente(idUtente));
    }

    @PostMapping("/salva")
    public void salva(@RequestBody UtenteDto u){
        utenteService.salvaDto(u);
    }


}
