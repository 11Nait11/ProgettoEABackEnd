package com.example.progettoprova.services.impl;

import com.example.progettoprova.dao.RecensioneDao;
import com.example.progettoprova.entities.Recensione;
import com.example.progettoprova.services.RecensioneService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RecensioneServiceImpl implements RecensioneService {
    private final RecensioneDao recensioneDao;

    @Override
    @SneakyThrows
    public List<Recensione> dammiRencesioni() {
        List<Recensione> recensioni = recensioneDao.findAll();
        if(recensioni.isEmpty())
            throw new Exception("Nessuna Rec");
        log.info("trovatoRece");
        return recensioni;
    }
}
