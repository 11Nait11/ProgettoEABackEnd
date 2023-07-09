package com.example.progettoprova.controller;


import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.services.ProdottoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/prodotto-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Prodotto", description = "Gestisci i tuoi prodotti con facilità")
public class ProdottoController {

    private final ProdottoService prodottoService;


    @Operation(description = "Restituisce tutti i prodotti disponibili")
    @ApiResponse(description = "Elenco dei prodotti restituito con successo", responseCode = "200")

    @GetMapping("prodotti")
    public ResponseEntity<List<ProdottoDto>>dammiProdotti(){
        return ResponseEntity.ok(prodottoService.dammiProdotti());
    }


    @Operation(description = "Restituisce i prodotti associati a un utente dato l'ID dell'utente")
    @ApiResponse(description = "Elenco dei prodotti dell'utente restituito con successo", responseCode = "200")
    @Parameter(name = "idUtente", description = "L'ID dell'utente", required = true, example = "123")

    @GetMapping("prodotti/utente/{idUtente}")
    public ResponseEntity<List<ProdottoDto>> dammiProdottiDiUnUtenteById(@PathVariable("idUtente") Long id){
        return ResponseEntity.ok(prodottoService.dammiProdottiDiUnUtenteById(id));
    }


    @Operation(description = "Aggiorna un prodotto dato l'ID del prodotto e i nuovi dati")
    @ApiResponse(description = "Prodotto aggiornato con successo", responseCode = "200")
    @Parameter(name = "idProdotto", description = "L'ID del prodotto", required = true, example = "456")

    @PutMapping("prodotti/{idProdotto}")
    public ResponseEntity<ProdottoDto> aggiorna(@PathVariable("idProdotto") Long id,@RequestBody ProdottoDto prodotto){
        return ResponseEntity.ok(prodottoService.aggiorna(id,prodotto));
    }


    @Operation(description = "Cancella un prodotto dato l'ID del prodotto")
    @ApiResponse(description = "Prodotto cancellato con successo", responseCode = "200")
    @Parameter(name = "idProdotto", description = "L'ID del prodotto", required = true, example = "789")

    @DeleteMapping("prodotti/{idProdotto}")
    public HttpStatus cancella(@PathVariable Long idProdotto){
        prodottoService.cancella(idProdotto);
        return HttpStatus.OK;
    }


    @Operation(description = "Salva un nuovo prodotto")
    @ApiResponse(description = "Prodotto salvato con successo", responseCode = "200")

    @PostMapping("salva")
    public HttpStatus salva(@RequestBody ProdottoDto p){
        log.info("salva prodotto");
        prodottoService.salva(p);
        return HttpStatus.OK;
    }


    @Operation(description = "Restituisce i prodotti di un venditore ordinati per prezzo crescente")
    @ApiResponse(description = "Elenco dei prodotti dell'utente ordinato per prezzo crescente restituito con successo", responseCode = "200")
    @Parameter(name = "id", description = "L'ID dell'utente", required = true, example = "123")

    @GetMapping("prodotti-venditore-crescByPrezzo")
    public ResponseEntity<List<ProdottoDto>> dammiProdittiUtenteByIdOrdCrescByPrzzo(@RequestParam Long id)
    {
        return ResponseEntity.ok(prodottoService.dammiProdottiDiUnUtenteByIdOrdCrescByPrezzo(id));
    }





    @GetMapping("prodotti/{idProdotto}")
    public ResponseEntity<ProdottoDto> getProdottoByIdProdotto(@PathVariable Long idProdotto){
        ProdottoDto prodottoDto = prodottoService.dammiProdottoById(idProdotto);
        return ResponseEntity.ok(prodottoDto);
    }


}
