package com.example.progettoprova.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtenteDto {


    private Long id;
    @NotBlank(message = "{firstname.notempty}")
    private String nome;
    @NotBlank(message = "{lastname.notempty}")
    private String cognome;
    @Email(message = "{email.notvalid}")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",message = "{password.notvalid}")
    private String password;
    private String roles; //separati da ,

}
