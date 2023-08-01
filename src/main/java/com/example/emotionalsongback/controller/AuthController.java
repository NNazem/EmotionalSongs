package com.example.emotionalsongback.controller;

import com.example.emotionalsongback.dto.JwtAuthResponse;
import com.example.emotionalsongback.dto.LoginDto;
import com.example.emotionalsongback.dto.RegisterDto;
import com.example.emotionalsongback.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller per la gestione delle operazioni di autenticazione e registrazione degli utenti.
 */
@CrossOrigin("*") // Consente le richieste da qualsiasi origine
@AllArgsConstructor
@RestController // Indica che si tratta di un controller REST
@RequestMapping("/api/auth") //Indica il percorso di base per le richieste HTTP di questo controller
public class AuthController {

    private AuthService authService;

    /**
     * Gestisce la richiesta di registrazione di un nuovo utente.
     *
     * @param registerDto i dati dell'utente da registrare
     * @return una ResponseEntity che contiene una String di conferma e lo stato HTTP CREATED
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * Gestisce la richiesta di login di un utente esistente.
     *
     * @param loginDto i dati di login dell'utente
     * @return una ResponseEntity che contiene una String di conferma e lo stato HTTP OK
     */
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
