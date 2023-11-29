package com.example.emotionalsongback.service.impl;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.entity.Canzone;
import com.example.emotionalsongback.exception.APIException;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.repository.CanzoneRepository;
import com.example.emotionalsongback.response.CanzoniResponse;
import com.example.emotionalsongback.service.CanzoneService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public CanzoneDto getCanzone(Long id) {
        Canzone canzone = canzoneRepository.findById(id).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + id));
        return modelMapper.map(canzone, CanzoneDto.class);
    }

    @Override
    public CanzoniResponse getCanzoniByTitolo(String titolo, String orderBy, String orderDirection, String page) {

        Sort sort = Sort.by(orderDirection.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, 10, sort);

        Page<Canzone> pagineCanzoni = canzoneRepository.findAllByTitoloContainingIgnoreCase(titolo, pageable);
        long numeroPagine = pagineCanzoni.getTotalPages();
        long numeroCanzoni = pagineCanzoni.getTotalElements();

        if (pagineCanzoni.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Nessuna canzone corrisponde ai termini di ricerca");
        }

        return new CanzoniResponse(pagineCanzoni.stream().map((canzone -> modelMapper.map(canzone, CanzoneDto.class))).collect(Collectors.toList())
                , numeroPagine, numeroCanzoni);
    }


    @Override
    public CanzoniResponse getCanzoniByAutoreAndAnno(String anno, String autore, String orderBy, String orderDirection, String page) {

        Sort sort = Sort.by(orderDirection.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, 10, sort);

        Page<Canzone> pagineCanzoni = canzoneRepository.findAllByAnnoAndAutoreContainingIgnoreCase(anno,autore, pageable);
        long numeroPagine = pagineCanzoni.getTotalPages();
        long numeroCanzoni = pagineCanzoni.getTotalElements();

        if (pagineCanzoni.isEmpty())
            throw new APIException(HttpStatus.NOT_FOUND, "Nessuna canzone corrisponde ai termini di ricerca");

        return new CanzoniResponse(pagineCanzoni.stream().map((canzone -> modelMapper.map(canzone, CanzoneDto.class))).collect(Collectors.toList()), numeroPagine, numeroCanzoni);
    }

    @Override
    public List<CanzoneDto> getCanzoniByTitoloAndAutore(String titolo, String autore) {
        List<Canzone> canzoni = canzoneRepository.findByTitoloContainingIgnoreCaseAndAutoreContainingIgnoreCase(titolo, autore);

        if (canzoni.isEmpty())
            throw new APIException(HttpStatus.NOT_FOUND, "Nessuna canzone corrisponde ai termini di ricerca");

        return canzoni.stream().map((canzone -> modelMapper.map(canzone, CanzoneDto.class))).collect(Collectors.toList());
    }

}
