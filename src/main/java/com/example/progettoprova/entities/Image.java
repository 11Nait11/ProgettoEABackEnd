package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue
    public Long id;



    @Lob
    public byte[] image;


}
