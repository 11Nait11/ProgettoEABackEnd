package com.example.progettoprova.services;

import com.example.progettoprova.dao.UtenteDao;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Utente;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtenteServiceImp implements UtenteService{

    private final UtenteDao utenteDao;
    private final ModelMapper modelMapper;

    @Override
    public List<UtenteDto> dammiUtenti(){
//        Utente u1=new Utente();
//        u1.setFirstName("Paperino");
//        u1.setLastName("Bianchi");
//        utenteDao.save(u1);
//
//        Utente u2=new Utente();
//        u2.setFirstName("Archimede");
//        u2.setLastName("Rossi");
//        utenteDao.save(u2);

        List<Utente> utenti = utenteDao.findAll();
        if(utenti.isEmpty())
            throw new EntityNotFoundException("Utenti non trovati!");
        return utenti.stream().map(u->modelMapper.map(u, UtenteDto.class)).collect(Collectors.toList());
    }
}
