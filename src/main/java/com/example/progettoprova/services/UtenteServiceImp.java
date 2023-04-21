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

    private void creaDb(){
        Utente u1=new Utente();
        u1.setFirstName("Paperino");
        u1.setLastName("Bianchi");
        utenteDao.save(u1);

        Utente u2=new Utente();
        u2.setFirstName("Archimede");
        u2.setLastName("Rossi");
        utenteDao.save(u2);
    }

    @Override
    public List<UtenteDto> dammiUtenti(){
//        creaDb();
        List<Utente> utenti = utenteDao.findAll();
        if(utenti.isEmpty())
            throw new EntityNotFoundException("Utenti non trovati!");
        return utenti.stream().map(u->modelMapper.map(u, UtenteDto.class)).collect(Collectors.toList());
    }

    @Override
    public UtenteDto dammiUtente(Long id) {
        creaDb();
        System.out.println("ServiceDammiUtente");
        Utente utente=utenteDao.findById(id).orElseThrow(()-> new EntityNotFoundException("Id non Presente"));
        if(utente!=null){
            System.out.println(utente.getFirstName());
        }

        return modelMapper.map(utente,UtenteDto.class);
    }

    @Override
    public void salva(Utente u) {utenteDao.save(u);}

    @Override
    public void salvaDto(UtenteDto uD) {
        Utente u= modelMapper.map(uD, Utente.class);
        utenteDao.save(u);
    }

    @Override
    public void cancella() {

    }
}
