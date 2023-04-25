package com.example.progettoprova.controller;

import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/utente-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteService utenteService;

    @GetMapping("utenti")//ok
    public ResponseEntity<List<UtenteDto>> dammiUtenti(){
        return ResponseEntity.ok(utenteService.dammiUtenti());
    }

    @GetMapping("prodotti-venditore")//ok
    public ResponseEntity<List<ProdottoDto>> dammiProdottiVenditore(@RequestParam Long id){
        System.out.println("t"+utenteService.dammiProdottiUtente(id));
        return ResponseEntity.ok(utenteService.dammiProdottiUtente(id));
    }



    @GetMapping("utenti/{idUtente}")
    public ResponseEntity<UtenteDto> dammiUtente(@PathVariable Long idUtente){
        return ResponseEntity.ok(utenteService.dammiUtente(idUtente));
    }

    @PostMapping("/salva")
    public HttpStatus salva(@RequestBody UtenteDto u){
        utenteService.salvaDto(u);
        return HttpStatus.OK;
    }

    @PutMapping("utenti/{idUtente}")
    public ResponseEntity<UtenteDto> aggiorna(@PathVariable("idUtente") Long id, @RequestBody UtenteDto utente) {
        return ResponseEntity.ok(utenteService.aggiorna(id, utente));
    }

    @DeleteMapping("utenti/{idUtente}")
    public HttpStatus cancella(@PathVariable("idUtente") Long id){
        utenteService.cancella(id);
        return HttpStatus.OK;
    }


}
