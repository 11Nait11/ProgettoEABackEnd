package com.example.progettoprova.services.impl;

import com.example.progettoprova.dao.MessaggioDao;
import com.example.progettoprova.dto.MessaggioDto;
import com.example.progettoprova.entities.Messaggio;
import com.example.progettoprova.exception.ImageException;
import com.example.progettoprova.exception.MessaggioException;
import com.example.progettoprova.services.MessaggioService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessaggioServiceImpl implements MessaggioService {

    private final MessaggioDao messaggioDao;
    private final ModelMapper modelMapper;

    @Override
    @SneakyThrows
    public List<MessaggioDto> dammiMessaggiUtenteById(Long id) {
        List<Messaggio> messaggi=messaggioDao.dammiMessaggiUtenteById(id);
        if(messaggi.isEmpty())
            throw new MessaggioException("Recensioni non presenti");
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
}
