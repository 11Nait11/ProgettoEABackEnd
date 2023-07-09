package com.example.progettoprova.controller;

import com.example.progettoprova.dto.RecensioneDto;
import com.example.progettoprova.services.RecensioneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recensione-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Recensioni", description = "Gestisci le recensioni")
public class RecensioneController {

    private final RecensioneService recensioneService;
    private final ModelMapper modelMapper;




    @Operation(description = "Restituisce tutte le recensioni")
    @ApiResponse(description = "Recensioni restituite con successo", responseCode = "200")
    @GetMapping("recensioni")
    public ResponseEntity<List<RecensioneDto>> dammiRecensioni() {
        return ResponseEntity.ok(recensioneService.dammiRencesioni());
    }



    @Operation(description = "Crea una nuova recensione")
    @ApiResponse(description = "Recensione creata con successo", responseCode = "200")
    @PostMapping("crea-recensione")
    public ResponseEntity<RecensioneDto> creaRecensione(@RequestBody RecensioneDto r) {
        recensioneService.creaRecensione(r);
        return ResponseEntity.ok(r);
    }


    //Dato l'id di un venditore restituisce tutte le recensioni che ha ricevuto quel venditore
    @GetMapping("recensioni/{idUtente}")
    public ResponseEntity<List<RecensioneDto>> dammiRecensioniByIdUtente(@PathVariable Long idUtente) {
        List<RecensioneDto> recensioniDto = recensioneService.dammiRencesioniByIdUtenteRecensito(idUtente);
        return ResponseEntity.ok(recensioniDto);
    }

}
