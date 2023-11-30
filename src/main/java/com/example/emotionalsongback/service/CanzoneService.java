package com.example.emotionalsongback.service;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.entity.Canzone;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.response.CanzoniResponse;

import java.util.List;

public interface CanzoneService {

    CanzoneDto getCanzone(Long id);

    CanzoniResponse getCanzoniByTitolo(String titolo, String orderBy, String orderDirection, String page);

    CanzoniResponse getCanzoniByAutoreAndAnno(String anno, String autore, String orderBy, String orderDirection, String page);

    CanzoniResponse getCanzoniByTitoloAndAutore(String titolo, String autore, String orderBy, String orderDirection, String page);
}
