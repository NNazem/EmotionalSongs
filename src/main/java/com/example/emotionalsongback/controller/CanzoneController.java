package com.example.emotionalsongback.controller;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.response.CanzoniResponse;
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
    public ResponseEntity<CanzoniResponse> getCanzoniByTitolo(@RequestParam("titolo") String titolo,
                                                               @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy,
                                                               @RequestParam(value = "orderDirection", required = false, defaultValue = "ASC") String orderDirection,
                                                               @RequestParam(value = "page", required = true, defaultValue = "1") String page){
        CanzoniResponse getCanzoni = canzoneService.getCanzoniByTitolo(titolo, orderBy, orderDirection, page);
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
    public ResponseEntity<CanzoniResponse> getCanzoniByAnnoAndAutore(@RequestParam("anno") String anno,
                                                                      @RequestParam("autore") String autore,
                                                                      @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy,
                                                                      @RequestParam(value = "orderDirection", required = false, defaultValue = "ASC") String orderDirection,
                                                                      @RequestParam(value = "page", required = true, defaultValue = "1") String page){
        CanzoniResponse getCanzoni = canzoneService.getCanzoniByAutoreAndAnno(anno,autore, orderBy, orderDirection, page);
        return ResponseEntity.ok(getCanzoni);
    }

    @GetMapping("/byTitoloAndAutore")
    public ResponseEntity<CanzoniResponse>  getCanzoniByTitoloAndAutore(@RequestParam("titolo") String titolo,
                                                                        @RequestParam("autore") String autore,
                                                                        @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy,
                                                                        @RequestParam(value = "orderDirection", required = false, defaultValue = "ASC") String orderDirection,
                                                                        @RequestParam(value = "page", required = true, defaultValue = "1") String page) {
        CanzoniResponse getCanzoni = canzoneService.getCanzoniByTitoloAndAutore(titolo,autore, orderBy, orderDirection, page);
        return ResponseEntity.ok(getCanzoni);
    }
}