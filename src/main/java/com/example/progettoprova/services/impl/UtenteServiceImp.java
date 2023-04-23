package com.example.progettoprova.services.impl;

import com.example.progettoprova.dao.UtenteDao;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Utente;
import com.example.progettoprova.exception.UtenteException;
import com.example.progettoprova.conf.UtenteExceptionMex;
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
public class UtenteServiceImp implements UtenteService {

    private final UtenteDao utenteDao;
    private final ModelMapper modelMapper;




    @Override
    @SneakyThrows//ok
    public List<UtenteDto> dammiUtenti(){
        List<Utente> utenti = utenteDao.findAll();
        if(utenti.isEmpty())
            throw new UtenteException("Utenti non trovati!");
        return utenti.stream().map(u->modelMapper.map(u, UtenteDto.class)).collect(Collectors.toList());}


    @Override
    @SneakyThrows//ok
    public UtenteDto dammiUtente(Long id) {

        Utente utente=utenteDao.findById(id).orElseThrow(()-> new UtenteException("Utente non presente"));
        return modelMapper.map(utente,UtenteDto.class);}


    @Override
    public void salva(Utente u) {
        utenteDao.save(u);
        log.info(UtenteExceptionMex.UTENTE_SALVATO_ID_LOG, u.getLastName());}


    @Override
    public void salvaDto(UtenteDto uD) {
        Utente u= modelMapper.map(uD, Utente.class);
        utenteDao.save(u);
        log.info(UtenteExceptionMex.UTENTE_SALVATO_ID_LOG, u.getLastName());}


    @Override
    @SneakyThrows
    public void cancella(Long id) {
        Optional<Utente> u = utenteDao.findById(id);
        if(u.isEmpty())
            throw new UtenteException(UtenteExceptionMex.UTENTE_NON_TROVATO_ID+id);
        utenteDao.delete(u.get());
        log.info(UtenteExceptionMex.UTENTE_CANCELLATO_ID_LOG, id);

    }


    @Override
    @SneakyThrows
    public UtenteDto aggiorna(Long id, UtenteDto utente) {
        Optional<Utente> u = utenteDao.findById(id);
        if(u.isEmpty())
            throw new UtenteException(UtenteExceptionMex.UTENTE_NON_TROVATO_ID+id);

        u.get().setFirstName(utente.getFirstName());
        u.get().setLastName(utente.getLastName());
        log.info(UtenteExceptionMex.UTENTE_AGGIORNATO_ID_LOG, id);
        return modelMapper.map(utenteDao.save(u.get()),UtenteDto.class);
    }
}
