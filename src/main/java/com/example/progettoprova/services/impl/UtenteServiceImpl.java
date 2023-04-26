package com.example.progettoprova.services.impl;

import com.example.progettoprova.dao.UtenteDao;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Utente;
import com.example.progettoprova.exception.ProdottoException;
import com.example.progettoprova.exception.UtenteException;
import com.example.progettoprova.conf.ExceptionMex;
import com.example.progettoprova.services.UtenteService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtenteServiceImpl implements UtenteService {

    private final UtenteDao utenteDao;
    private final ModelMapper modelMapper;




    @Override
    @SneakyThrows//ok
    public List<UtenteDto> dammiUtenti(){
        List<Utente> utenti = utenteDao.findAll();
        if(utenti.isEmpty())
            throw new UtenteException(ExceptionMex.UTENTI_NON_TROVATI);
        System.out.println("Utenti: "+utenti);
        List<UtenteDto> utendto = utenti.stream().map(u -> modelMapper.map(u, UtenteDto.class)).collect(Collectors.toList());
        System.out.println("Utentidto: "+utendto);
        return  utendto;
    }

    @Override
    @SneakyThrows
    public List<ProdottoDto> dammiProdottiUtente(Long id) {

        List<com.example.progettoprova.entities.Prodotto> prodotti=utenteDao.cercaProdottiByIdUtente(id);
        if(prodotti.isEmpty())
            throw new ProdottoException(ExceptionMex.PRODOTTI_NON_TROVATI);

        return prodotti.stream().map(prodotto -> modelMapper.map(prodotto, ProdottoDto.class)).collect(Collectors.toList());

    }


    @Override
    @SneakyThrows//ok
    public UtenteDto dammiUtente(Long id) {

        Utente utente=utenteDao.findById(id).orElseThrow(()-> new UtenteException(ExceptionMex.UTENTE_NON_TROVATO_ID+id));
        return modelMapper.map(utente,UtenteDto.class);}


    @Override
    public void salva(Utente u) {
        utenteDao.save(u);
        log.info(ExceptionMex.UTENTE_SALVATO_NOME_LOG, u.getCognome());}


    @Override
    public void salvaDto(UtenteDto uD) {
        Utente u= modelMapper.map(uD, Utente.class);
        utenteDao.save(u);
        log.info(ExceptionMex.UTENTE_SALVATO_NOME_LOG, u.getCognome());}


    @Override
    @SneakyThrows
    public void cancella(Long id) {
        Optional<Utente> u = utenteDao.findById(id);
        if(u.isEmpty())
            throw new UtenteException(ExceptionMex.UTENTE_NON_TROVATO_ID+id);
        utenteDao.delete(u.get());
        log.info(ExceptionMex.UTENTE_CANCELLATO_ID_LOG, id);

    }


    @Override
    @SneakyThrows
    public UtenteDto aggiorna(Long id, UtenteDto utente) {
        Optional<Utente> u = utenteDao.findById(id);
        if(u.isEmpty())
            throw new UtenteException(ExceptionMex.UTENTE_NON_TROVATO_ID+id);

        u.get().setNome(utente.getNome());
        u.get().setCognome(utente.getCognome());
        log.info(ExceptionMex.UTENTE_AGGIORNATO_ID_LOG, id);
        return modelMapper.map(utenteDao.save(u.get()),UtenteDto.class);
    }
}
