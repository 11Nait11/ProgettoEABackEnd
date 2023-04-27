package com.example.progettoprova.services.impl;

import com.example.progettoprova.conf.ExceptionMex;
import com.example.progettoprova.dao.RecensioneDao;
import com.example.progettoprova.dto.RecensioneDto;
import com.example.progettoprova.entities.Recensione;
import com.example.progettoprova.exception.RecensioneException;
import com.example.progettoprova.services.RecensioneService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RecensioneServiceImpl implements RecensioneService {
    private final RecensioneDao recensioneDao;
    private final ModelMapper modelMapper;

    @Override
    @SneakyThrows
    public List<RecensioneDto> dammiRencesioni() {
        List<Recensione> recensioni = recensioneDao.findAll();
        if(recensioni.isEmpty())
            throw new RecensioneException(ExceptionMex.RENCENSIONI_NON_TROVATE);
        log.info("Recensioni Trovate");
        return recensioni.stream().map(r->modelMapper.map(r,RecensioneDto.class)).collect(Collectors.toList());
    }

    @Override
    public RecensioneDto creaRecensione(RecensioneDto r) {
        recensioneDao.save(modelMapper.map(r,Recensione.class));
        return r;
    }
}
