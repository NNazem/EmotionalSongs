package com.example.emotionalsongback.service;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.entity.Canzone;
import com.example.emotionalsongback.exception.ResourceNotFoundException;

import java.util.List;

public interface CanzoneService {

    CanzoneDto getCanzone(Long id);

    List<CanzoneDto> getCanzoniByTitolo(String titolo);

    List<CanzoneDto> getCanzoniByAutoreAndAnno(String anno, String autore);
}
