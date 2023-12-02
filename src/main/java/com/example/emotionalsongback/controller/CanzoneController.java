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

/**
 * Controller per la gestione delle richieste relative alle canzoni.
 * Fornisce endpoint per la ricerca e il recupero di informazioni sulle canzoni.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/canzone")
@AllArgsConstructor
public class CanzoneController {

    private CanzoneService canzoneService;

    /**
     * Ottiene una lista di canzoni filtrate per titolo.
     *
     * @param titolo Il titolo della canzone da cercare.
     * @param orderBy Campo per l'ordinamento delle canzoni (default "id").
     * @param orderDirection Direzione dell'ordinamento, ASC o DESC (default "ASC").
     * @param page Numero di pagina per la paginazione dei risultati (default "1").
     * @return ResponseEntity contenente una lista di CanzoneDto e lo stato HTTP OK.
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
     * Ottiene una lista di canzoni filtrate per autore e anno.
     *
     * @param anno L'anno della canzone da cercare.
     * @param autore L'autore della canzone da cercare.
     * @param orderBy Campo per l'ordinamento delle canzoni (default "id").
     * @param orderDirection Direzione dell'ordinamento, ASC o DESC (default "ASC").
     * @param page Numero di pagina per la paginazione dei risultati (default "1").
     * @return ResponseEntity contenente una lista di CanzoneDto e lo stato HTTP OK.
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

    /**
     * Ottiene una lista di canzoni filtrate per titolo e autore.
     *
     * @param titolo Il titolo della canzone da cercare.
     * @param autore L'autore della canzone da cercare.
     * @param orderBy Campo per l'ordinamento delle canzoni (default "id").
     * @param orderDirection Direzione dell'ordinamento, ASC o DESC (default "ASC").
     * @param page Numero di pagina per la paginazione dei risultati (default "1").
     * @return ResponseEntity contenente una lista di CanzoneDto e lo stato HTTP OK.
     */
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
