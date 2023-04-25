package com.example.progettoprova.services.impl;

import com.example.progettoprova.conf.ExceptionMex;
import com.example.progettoprova.dao.ProdottoDao;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.exception.ProdottoException;
import com.example.progettoprova.services.ProdottoService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class ProdottoServiceImpl implements ProdottoService {

    private final ProdottoDao prodottoDao;
    private final ModelMapper modelMapper;

    @Override
    @SneakyThrows
    public List<ProdottoDto> dammiProdotti() {
        List<Prodotto> prodotti = prodottoDao.findAll();
        if(prodotti.isEmpty())
            throw new ProdottoException(ExceptionMex.PRODOTTI_NON_TROVATI);
        return prodotti.stream().map(prodotto -> modelMapper.map(prodotto,ProdottoDto.class)).collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public List<ProdottoDto> dammiProdottiVenditore(Long id) {
         List<Prodotto> prodotti=prodottoDao.findAllByVenditoreId(id);
         if(prodotti.isEmpty())
             throw new ProdottoException(ExceptionMex.PRODOTTI_NON_TROVATI);


        return prodotti.stream().map(prodotto -> modelMapper.map(prodotto,ProdottoDto.class)).collect(Collectors.toList());
    }

    @Override
    public void salva(ProdottoDto u) {

    }

    @Override
    public void salvaDto(ProdottoDto uD) {

    }

    @Override
    public void cancella(Long id) {

    }

    @Override
    public ProdottoDto aggiorna(Long id, ProdottoDto utente) {
        return null;
    }
}
