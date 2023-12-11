package com.example.emotionalsongback.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;
    private String username;
    private String codiceFiscale;
    private String password;
}
