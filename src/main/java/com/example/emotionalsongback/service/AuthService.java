package com.example.emotionalsongback.service;

import com.example.emotionalsongback.dto.LoginDto;
import com.example.emotionalsongback.dto.RegisterDto;
import com.example.emotionalsongback.entity.Utente;

public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    Utente getUtenteFromAuthentication();
}
