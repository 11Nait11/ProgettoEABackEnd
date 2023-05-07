package com.example.progettoprova.controller;


import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.services.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotto-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ProdottoController {

    private final ProdottoService prodottoService;


//    ok
    @GetMapping("prodotti")
    public ResponseEntity<List<ProdottoDto>>dammiProdotti(){
        return ResponseEntity.ok(prodottoService.dammiProdotti());
    }

    @GetMapping("prodotti/{idProdotto}")
    public ResponseEntity<ProdottoDto> dammiProdottoById(@PathVariable("idProdotto") Long id){
        return ResponseEntity.ok(prodottoService.dammiProdottoById(id));
    }

    @GetMapping("prodotti/utente/{idUtente}")
    public ResponseEntity<List<ProdottoDto>> dammiProdottiDiUnUtenteById(@PathVariable("idUtente") Long id){
        return ResponseEntity.ok(prodottoService.dammiProdottiDiUnUtenteById(id));
    }

    @PutMapping("prodotti/{idProdotto}")
    public ResponseEntity<ProdottoDto> aggiorna(@PathVariable("idProdotto") Long id,@RequestBody ProdottoDto prodotto){
        return ResponseEntity.ok(prodottoService.aggiorna(id,prodotto));
    }

    @DeleteMapping("prodotti/{idProdotto}")
    public HttpStatus cancella(@PathVariable Long idProdotto){
        prodottoService.cancella(idProdotto);
        return HttpStatus.OK;
    }
    
//    ok

//    ok
    @PostMapping("salva")
    public HttpStatus salva(@RequestBody ProdottoDto p){
        prodottoService.salva(p);
        return HttpStatus.OK;
    }
    @GetMapping("prodotti-venditore-crescByPrezzo")
    public ResponseEntity<List<ProdottoDto>> dammiProdittiUtenteByIdOrdCrescByPrzzo(@RequestParam Long id)
    {
        return ResponseEntity.ok(prodottoService.dammiProdottiDiUnUtenteByIdOrdCrescByPrezzo(id));
    }


}
