package com.example.emotionalsongback.controller;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.service.CanzoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/canzone")
@AllArgsConstructor
public class CanzoneController {

    private CanzoneService canzoneService;

    @GetMapping
    public ResponseEntity<List<CanzoneDto>> getCanzoniByTitolo(@RequestParam("titolo") String titolo){
        List<CanzoneDto> getCanzoni = canzoneService.getCanzoniByTitolo(titolo);
        return ResponseEntity.ok(getCanzoni);
    }

    @GetMapping("/byAutoreAndAnno")
    public ResponseEntity<List<CanzoneDto>> getCanzoniByAnnoAndAutore(@RequestParam("anno") String anno, @RequestParam String autore){
        List<CanzoneDto> getCanzoni = canzoneService.getCanzoniByAutoreAndAnno(anno,autore);
        return ResponseEntity.ok(getCanzoni);
    }
}
