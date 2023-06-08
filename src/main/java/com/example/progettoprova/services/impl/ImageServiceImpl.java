package com.example.progettoprova.services.impl;

import com.example.progettoprova.config.MessagesConfig;
import com.example.progettoprova.dao.ImageDao;
import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.entities.Image;
import com.example.progettoprova.exception.ImageException;
import com.example.progettoprova.services.ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageDao imageDao;
    private final ModelMapper modelMapper;


    @Override
    @SneakyThrows
    @Transactional
    public List<ImageDto> dammiImmaginiByIdProdotto(Long id) {
        List<Image> immagini=imageDao.dammiImmaginiByIdProdotto(id);
//        List<byte[]> immagini = imageDao.cercaImagesByIdProdotto(id);
        System.out.println("Server sono dentro "+immagini);
        if (immagini.isEmpty())
            throw new ImageException(MessagesConfig.IMAGES_NON_TROVATO_ID_PRODOTTO + id);

        return immagini.stream().map(i ->modelMapper.map(i, ImageDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ImageDto> dammiImmagini() {
        List<Image> immagini=imageDao.dammiUnaImagePerProdotto();
        return immagini.stream().map(i ->modelMapper.map(i, ImageDto.class)).collect(Collectors.toList());
    }


    @Override
    public void salva(Image i) {
        imageDao.save(i);
        log.info(MessagesConfig.IMMAGINE_SALVATO_LOG);
    }

    @Override
    @SneakyThrows
    public void cancella(Long id) {
        Optional<Image> i = imageDao.findById(id);
        if(i.isEmpty())
            throw new ImageException(MessagesConfig.IMAGES_NON_TROVATO_ID +id);
        imageDao.delete(i.get());
        log.info(MessagesConfig.IMMAGINE_CANCELLATO_ID_LOG+id);
    }



    @Override
    public ProdottoDto aggiorna(Long id, Image i) {
        return null;
    }
}
