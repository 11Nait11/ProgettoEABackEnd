package com.example.progettoprova.controller;

import com.example.progettoprova.dao.ImageDao;
import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Image;
import com.example.progettoprova.services.ImageService;
import com.example.progettoprova.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/image-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ImageController {


    private final ImageService imageService;



    @GetMapping("images")
    public ResponseEntity<List<ImageDto>> dammiImmagini() {
        return ResponseEntity.ok(imageService.dammiImmagini());
    }



    @GetMapping("images/{idProdotto}")
    public ResponseEntity<List<ImageDto>> dammiImagesByIdProdotto(@PathVariable("idProdotto") Long idProdotto){
        return ResponseEntity.ok(imageService.dammiImmaginiByIdProdotto(idProdotto));
    }

    @DeleteMapping("images/{idImage}")
    public HttpStatus cancella(@PathVariable("idImage") Long id){
        imageService.cancella(id);
        return HttpStatus.OK;
    }

    @PostMapping("salva")
    public HttpStatus salvaImage(@RequestBody Image i){
        imageService.salva(i);
        return HttpStatus.OK;
    }







}
