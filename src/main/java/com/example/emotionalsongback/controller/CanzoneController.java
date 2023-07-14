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

    /**
     * Gestisce la richiesta GET per ricercare canzoni tramite titolo.
     *
     * @param titolo il titolo della canzone da cercare
     * @return una ResponseEntity che contiene una lista di oggetti CanzoneDto e lo stato HTTP OK
     */
    @GetMapping
    public ResponseEntity<List<CanzoneDto>> getCanzoniByTitolo(@RequestParam("titolo") String titolo){
        List<CanzoneDto> getCanzoni = canzoneService.getCanzoniByTitolo(titolo);
        return ResponseEntity.ok(getCanzoni);
    }

    /**
     * Gestisce la richiesta GET per ricercare canzoni tramite autore e anno.
     *
     * @param anno l'anno della canzone da cercare
     * @param autore l'autore della canzone da cercare
     * @return una ResponseEntity che contiene una lista di oggetti CanzoneDto e lo stato HTTP OK
     */
    @GetMapping("/byAutoreAndAnno")
    public ResponseEntity<List<CanzoneDto>> getCanzoniByAnnoAndAutore(@RequestParam("anno") String anno, @RequestParam String autore){
        List<CanzoneDto> getCanzoni = canzoneService.getCanzoniByAutoreAndAnno(anno,autore);
        return ResponseEntity.ok(getCanzoni);
    }
}
