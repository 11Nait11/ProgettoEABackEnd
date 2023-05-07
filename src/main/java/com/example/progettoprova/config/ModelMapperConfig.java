package com.example.progettoprova.config;

import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.entities.Image;
import com.example.progettoprova.entities.Prodotto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
//     utilizzo nomi dei campi per mappare:
//      .setFieldMatchingEnabled(true)
//    accedo anche sui campi privati :
//      .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        modelMapper.createTypeMap(Image.class, ImageDto.class)
                .addMapping(Image::getImage, ImageDto::setImage);//nome Campo (getPippo setTopolino)1
        modelMapper.createTypeMap(ImageDto.class, Image.class)
                .addMapping(ImageDto::getImage, Image::setImage);
        // Mappa la lista di ImageDto all'elenco corrispondente di Image all'interno di Prodotto
        modelMapper.typeMap(ProdottoDto.class, Prodotto.class)
                .addMapping(ProdottoDto::getImages, Prodotto::setImages);
        return modelMapper;
    }
}
