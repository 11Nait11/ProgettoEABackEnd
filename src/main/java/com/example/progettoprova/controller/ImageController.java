package com.example.progettoprova.controller;

import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.entities.Image;
import com.example.progettoprova.services.ImmagineService;
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
@RequestMapping("/image-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Immagini", description = "Gestisci le Immagini")
public class ImageController {


    private final ImmagineService immagineService;




    @Operation(description = "Restituisce tutte le immagini")
    @ApiResponse(description = "Elenco delle immagini restituito con successo", responseCode = "200")

    @GetMapping("images")
    public ResponseEntity<List<ImageDto>> dammiImmagini() {
        return ResponseEntity.ok(immagineService.dammiImmagini());
    }


    @Operation(description = "Restituisce le immagini di un prodotto dato il suo ID")
    @ApiResponse(description = "Elenco delle immagini del prodotto restituito con successo", responseCode = "200")
    @Parameter(name = "idProdotto", description = "ID del prodotto", required = true, example = "1")

    @GetMapping("images/{idProdotto}")
    public ResponseEntity<List<ImageDto>> dammiImagesByIdProdotto(@PathVariable("idProdotto") Long idProdotto){
        return ResponseEntity.ok(immagineService.dammiImmaginiByIdProdotto(idProdotto));
    }


    @Operation(description = "Cancella un'immagine dato il suo ID")
    @ApiResponse(description = "Immagine cancellata con successo", responseCode = "200")
    @Parameter(name = "idImage", description = "ID dell'immagine", required = true, example = "1")

    @DeleteMapping("images/{idImage}")
    public HttpStatus cancella(@PathVariable("idImage") Long id){
        immagineService.cancella(id);
        return HttpStatus.OK;
    }


    @Operation(description = "Salva una nuova immagine")
    @ApiResponse(description = "Immagine salvata con successo", responseCode = "200")

    @PostMapping("salva")
    public HttpStatus salvaImage(@RequestBody Image i){
        immagineService.salva(i);
        return HttpStatus.OK;
    }







}
