package com.example.progettoprova.controller;


import com.example.progettoprova.dao.ProdottoDao;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.services.ProdottoService;
import com.example.progettoprova.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotto-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ProdottoController {

    private final ProdottoService prodottoService;


    @GetMapping("prodotti")
    public ResponseEntity<List<ProdottoDto>>dammiProdotti(){
        return ResponseEntity.ok(prodottoService.dammiProdotti());
    }
    @GetMapping("prodotti-venditore")
    public ResponseEntity<List<ProdottoDto>>dammiProdottiVenditore(@RequestParam Long id){
        return ResponseEntity.ok(prodottoService.dammiProdottiVenditore(id));
    }


}
