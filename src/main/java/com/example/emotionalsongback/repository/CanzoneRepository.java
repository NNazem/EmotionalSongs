package com.example.emotionalsongback.repository;

import com.example.emotionalsongback.entity.Canzone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CanzoneRepository extends JpaRepository<Canzone, Long> {
    List<Canzone> findByTitoloContainingIgnoreCase(String titolo);

    List<Canzone> findByAnnoAndAutoreContainingIgnoreCase(String anno, String autore);
}
