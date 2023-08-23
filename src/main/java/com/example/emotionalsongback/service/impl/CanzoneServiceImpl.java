package com.example.emotionalsongback.service.impl;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.entity.Canzone;
import com.example.emotionalsongback.exception.APIException;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.repository.CanzoneRepository;
import com.example.emotionalsongback.service.CanzoneService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CanzoneServiceImpl implements CanzoneService {

    private CanzoneRepository canzoneRepository;

    private ModelMapper modelMapper;

    @Override
    public CanzoneDto getCanzone(Long id){
        Canzone canzone = canzoneRepository.findById(id).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND,"Canzone non trovata con id: " + id));
        return modelMapper.map(canzone,CanzoneDto.class);
    }

    @Override
    public List<CanzoneDto> getCanzoniByTitolo(String titolo){
        List<Canzone> canzoni = canzoneRepository.findByTitoloContainingIgnoreCase(titolo);

        if(canzoni.isEmpty()) throw new APIException(HttpStatus.NOT_FOUND, "Nessuna canzone corrisponde ai termini di ricerca");

        return canzoni.stream().map((canzone -> modelMapper.map(canzone,CanzoneDto.class))).collect(Collectors.toList());
    }

    @Override
    public List<CanzoneDto> getCanzoniByAutoreAndAnno(String anno, String autore) {
        List<Canzone> canzoni = canzoneRepository.findByAnnoAndAutoreContainingIgnoreCase(anno,autore);

        if(canzoni.isEmpty()) throw new APIException(HttpStatus.NOT_FOUND, "Nessuna canzone corrisponde ai termini di ricerca");

        return canzoni.stream().map((canzone -> modelMapper.map(canzone,CanzoneDto.class))).collect(Collectors.toList());
    }

}
