package com.example.progettoprova.config;

import com.example.progettoprova.dto.ImageDto;
import com.example.progettoprova.entities.Image;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        modelMapper.createTypeMap(Image.class, ImageDto.class)
                .addMapping(Image::getImage, ImageDto::setImage);
    return modelMapper;
    }
}
