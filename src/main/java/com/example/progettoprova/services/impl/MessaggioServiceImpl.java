package com.example.progettoprova.services.impl;

import com.example.progettoprova.config.MessagesConfig;
import com.example.progettoprova.dao.MessaggioDao;
import com.example.progettoprova.dto.MessaggioDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Messaggio;
import com.example.progettoprova.entities.Utente;
import com.example.progettoprova.exception.ImageException;
import com.example.progettoprova.exception.MessaggioException;
import com.example.progettoprova.services.MessaggioService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MessaggioServiceImpl implements MessaggioService {

    private final MessaggioDao messaggioDao;
    private final ModelMapper modelMapper;

    @Override
    @SneakyThrows
    public List<MessaggioDto> dammiMessaggiUtenteById(Long id) {
        List<Messaggio> messaggi=messaggioDao.dammiMessaggiUtenteById(id);
        if(messaggi.isEmpty())
            throw new MessaggioException(MessagesConfig.MESSAGGIO_NON_TROVATO_PER_UTENTE_ID+id);
        log.info("Restituiti messaggi per utente con id: "+id);
        return messaggi.stream().map(messaggio -> modelMapper.map(messaggio, MessaggioDto.class)).collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public List<MessaggioDto> dammiRecensioni() {
        List<Messaggio> messaggi=messaggioDao.findAll();
        if(messaggi.isEmpty())
            throw new Exception("Recensioni non presenti");
        return messaggi.stream().map(messaggio -> modelMapper.map(messaggio, MessaggioDto.class)).collect(Collectors.toList());
    }

    @Override
    public void salva(MessaggioDto m) {
        m.setDataInvio(LocalDateTime.now());
        messaggioDao.save(modelMapper.map(m,Messaggio.class));
        log.info(MessagesConfig.MESSAGGIO_SALVATO_LOG);
    }


}
