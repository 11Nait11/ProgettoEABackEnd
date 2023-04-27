package com.example.progettoprova.controller;

import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.dto.RecensioneDto;
import com.example.progettoprova.entities.Image;
import com.example.progettoprova.services.ImageService;
import com.example.progettoprova.services.RecensioneService;
import com.example.progettoprova.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/recensione-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class RecensioneController {

    private final RecensioneService recensioneService;
    private final ModelMapper modelMapper;

    @GetMapping("recensioni")
    public ResponseEntity<List<RecensioneDto>> dammiRecensioni() {
        return ResponseEntity.ok(recensioneService.dammiRencesioni());
    }

    @PostMapping("crea-recensione")
    public ResponseEntity<RecensioneDto> creaRecensione(@RequestBody RecensioneDto r) {
        recensioneService.creaRecensione(r);
        return ResponseEntity.ok(r);
    }





}
