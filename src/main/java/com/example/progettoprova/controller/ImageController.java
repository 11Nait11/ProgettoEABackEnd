package com.example.progettoprova.controller;

import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.entities.Image;
import com.example.progettoprova.services.ImmagineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/image-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ImageController {


    private final ImmagineService immagineService;



    @GetMapping("images")
    public ResponseEntity<List<ImageDto>> dammiImmagini() {
        return ResponseEntity.ok(immagineService.dammiImmagini());
    }



    @GetMapping("images/{idProdotto}")
    public ResponseEntity<List<ImageDto>> dammiImagesByIdProdotto(@PathVariable("idProdotto") Long idProdotto){
        return ResponseEntity.ok(immagineService.dammiImmaginiByIdProdotto(idProdotto));
    }

    @DeleteMapping("images/{idImage}")
    public HttpStatus cancella(@PathVariable("idImage") Long id){
        immagineService.cancella(id);
        return HttpStatus.OK;
    }

    @PostMapping("salva")
    public HttpStatus salvaImage(@RequestBody Image i){
        immagineService.salva(i);
        return HttpStatus.OK;
    }







}
