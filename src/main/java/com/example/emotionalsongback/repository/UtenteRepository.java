package com.example.emotionalsongback.repository;

import com.example.emotionalsongback.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Long> {

    Optional<Utente> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<Utente> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

}
