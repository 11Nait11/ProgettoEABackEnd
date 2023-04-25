package com.example.progettoprova.services.impl;

import com.example.progettoprova.conf.ExceptionMex;
import com.example.progettoprova.dao.ProdottoDao;

import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.entities.Utente;
import com.example.progettoprova.exception.ProdottoException;
import com.example.progettoprova.exception.UtenteException;
import com.example.progettoprova.services.ProdottoService;
import com.example.progettoprova.services.UtenteService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class ProdottoServiceImpl implements ProdottoService {

    private final ProdottoDao prodottoDao;
    private final ModelMapper modelMapper;
    private final UtenteService utenteService;

    @Override
    @SneakyThrows
    public List<ProdottoDto> dammiProdotti() {

        List<Prodotto> prodotti = prodottoDao.findAll();
        if(prodotti.isEmpty())
            throw new ProdottoException(ExceptionMex.PRODOTTI_NON_TROVATI);
        return prodotti.stream().map(prodotto -> modelMapper.map(prodotto, ProdottoDto.class)).collect(Collectors.toList());
    }

//    meglio implementarla in utente?
    @Override
    @SneakyThrows
    public List<ProdottoDto> dammiProdottiDiUnUtenteById(Long id) {
         List<Prodotto> prodotti=prodottoDao.findAllByVenditoreId(id);
         if(prodotti.isEmpty())
             throw new ProdottoException(ExceptionMex.PRODOTTI_NON_TROVATI);
        return prodotti.stream().map(prodotto -> modelMapper.map(prodotto, ProdottoDto.class)).collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public void salva(ProdottoDto p) {

        if(utenteService.dammiUtente(p.getVenditoreId())==null)
            throw new UtenteException(ExceptionMex.UTENTE_NON_TROVATO_ID+p.getVenditoreId());
        prodottoDao.save(modelMapper.map(p,Prodotto.class));
        log.info(ExceptionMex.PRODOTTO_SALVATO_NOME_LOG+p.getNomeProdotto());
    }




    @Override
    public void cancella(Long id) {

    }

    @Override
    @SneakyThrows
    public ProdottoDto aggiorna(Long id, ProdottoDto prodotto) {
        Optional<Prodotto> p = prodottoDao.findById(id);
        if(p.isEmpty())
            throw new ProdottoException(ExceptionMex.PRODOTTO_NON_TROVATO+id);
        p.get().setPrezzo(prodotto.getPrezzo());
        log.info(ExceptionMex.PRODOTTO_AGGIORNATO_ID_LOG, id);
        return modelMapper.map(prodottoDao.save(p.get()),ProdottoDto.class);
    }


}
