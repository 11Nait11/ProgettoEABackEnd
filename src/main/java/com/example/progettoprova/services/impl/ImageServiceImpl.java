package com.example.progettoprova.services.impl;

import com.example.progettoprova.conf.ExceptionMex;
import com.example.progettoprova.dao.ImageDao;
import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.entities.Image;
import com.example.progettoprova.exception.ImageException;
import com.example.progettoprova.exception.ProdottoException;
import com.example.progettoprova.services.ImageService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageDao imageDao;
    private final ModelMapper modelMapper;


    @Override
    @SneakyThrows
    public List<Image> dammiImageByIdProdotto(Long id) {
        List<Image> i = imageDao.cercaImagesByIdProdotto(id);
        if(i.isEmpty())
           throw  new ImageException(ExceptionMex.IMAGES_NON_TROVATO_ID_PRODOTTO + id);

        return i;}

    @Override
    public void salva(ImageDto i) {

        imageDao.save(modelMapper.map(i, Image.class));
        log.info(ExceptionMex.IMMAGINE_SALVATO_LOG);
    }

    @Override
    @SneakyThrows
    public void cancella(Long id) {
        Optional<Image> i = imageDao.findById(id);
        if(i.isEmpty())
            throw new ImageException(ExceptionMex.IMAGES_NON_TROVATO_ID +id);
        imageDao.delete(i.get());
        log.info(ExceptionMex.IMMAGINE_CANCELLATO_ID_LOG+id);
    }



    @Override
    public ProdottoDto aggiorna(Long id, Image i) {
        return null;
    }
}
