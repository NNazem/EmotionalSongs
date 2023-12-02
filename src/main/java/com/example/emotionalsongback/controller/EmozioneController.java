package com.example.emotionalsongback.controller;

import com.example.emotionalsongback.dto.EmozioneDto;
import com.example.emotionalsongback.entity.Emozione;
import com.example.emotionalsongback.repository.EmozioneRepository;
import com.example.emotionalsongback.service.EmozioneService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Controller per la gestione delle emozioni associate alle canzoni nelle playlist.
 * Fornisce endpoint API per aggiungere, ottenere e rimuovere emozioni.
 */
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class EmozioneController {

    private EmozioneService emozioneService;

    /**
     * Aggiunge un'emozione a una canzone in una playlist.
     * Richiede ruolo 'USER'.
     *
     * @param canzoneId Identificativo della canzone.
     * @param playlistId Identificativo della playlist.
     * @param emozioneDto Oggetto DTO per l'emozione da aggiungere.
     * @return ResponseEntity con la risposta del servizio e status HTTP OK.
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/api/playlist/{playlistId}/canzone/{canzoneId}/emozioni")
    public ResponseEntity<String> addEmozione(@PathVariable Long canzoneId, @PathVariable Long playlistId, @RequestBody EmozioneDto emozioneDto){
        String response = emozioneService.addEmozione(canzoneId, playlistId, emozioneDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Recupera le emozioni associate a una canzone.
     * Richiede ruolo 'USER'.
     *
     * @param canzoneId Identificativo della canzone.
     * @return ResponseEntity contenente un set di emozioni associate e status HTTP OK.
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/api/canzone/{canzoneId}/emozioni")
    public ResponseEntity<Map<Emozione.TipoEmozione, Record>> getEmozioni(@PathVariable Long canzoneId){
        Map<Emozione.TipoEmozione, Record> emozioniAssociate = emozioneService.getEmozioni(canzoneId);
        return new ResponseEntity<>(emozioniAssociate, HttpStatus.OK);
    }

    /**
     * Rimuove un'emozione da una canzone in una playlist.
     * Richiede ruolo 'USER'.
     *
     * @param canzoneId Identificativo della canzone.
     * @param playlistId Identificativo della playlist.
     * @param emozioneId Identificativo dell'emozione da rimuovere.
     * @return ResponseEntity con la risposta del servizio e status HTTP OK.
     */
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/api/playlist/{playlistId}/canzone/{canzoneId}/emozioni/{emozioneId}")
    public ResponseEntity<String> deleteEmozione(@PathVariable Long canzoneId, @PathVariable Long playlistId, @PathVariable Long emozioneId){
        String response = emozioneService.deleteEmozione(canzoneId, playlistId, emozioneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
