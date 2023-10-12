package com.example.emotionalsongback.repository;

import com.example.emotionalsongback.entity.Canzone;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CanzoneRepository extends JpaRepository<Canzone, Long> {

    List<Canzone> findByTitoloContainingIgnoreCase(String titolo,Sort sort);



    List<Canzone> findByAnnoAndAutoreContainingIgnoreCase(String anno, String autore);

}
