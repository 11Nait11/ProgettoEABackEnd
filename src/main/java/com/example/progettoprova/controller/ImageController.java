package com.example.progettoprova.controller;

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

    private final UtenteService utenteService;
    private final ImageService imageService;
    private final ModelMapper modelMapper;

    @GetMapping("images")//togliere logica da qui
    public ResponseEntity<List<ImageDto>> dammiImagesByIdProdotto(@RequestParam Long idProdotto){
        List<Image> images = imageService.dammiImageByIdProdotto(idProdotto);
        return ResponseEntity.ok( images.stream().map(i ->modelMapper.map(i,ImageDto.class)).collect(Collectors.toList()));
    }

    @DeleteMapping("images/{idImage}")
    public HttpStatus cancella(@PathVariable("idImage") Long id){
        imageService.cancella(id);
        return HttpStatus.OK;
    }

    @PostMapping("salva")
    public HttpStatus salvaImage(@RequestBody ImageDto i){
        imageService.salva(i);
        return HttpStatus.OK;
    }







}
