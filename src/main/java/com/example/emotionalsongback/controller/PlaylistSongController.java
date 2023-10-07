package com.example.emotionalsongback.controller;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.service.PlaylistCanzoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists/{playlistId}/canzoni")
@AllArgsConstructor
public class PlaylistSongController {

    private PlaylistCanzoneService playlistCanzoneService;

    /**
     * Gestisce la richiesta di aggiunta di una canzone a una playlist.
     *
     * @param playlistId l'ID della playlist a cui aggiungere la canzone
     * @return una ResponseEntity con lo stato HTTP CREATED
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> addCanzoneToPlaylist(@PathVariable Long playlistId, @RequestParam("id") Long canzoneId){
        playlistCanzoneService.addCanzoneToPlaylist(playlistId, canzoneId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Gestisce la richiesta di rimozione di una canzone da una playlist.
     *
     * @param playlistId l'ID della playlist da cui rimuovere la canzone
     * @param canzoneId l'ID della canzone da rimuovere
     * @return una ResponseEntity con lo stato HTTP OK
     */
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping
    public ResponseEntity<?> removeCanzoneFromPlaylist(@PathVariable Long playlistId, @RequestParam("id") Long canzoneId){
        playlistCanzoneService.removeCanzoneFromPlaylist(playlistId, canzoneId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Gestisce la richiesta di recupero di tutte le canzoni in una playlist.
     *
     * @param playlistId l'ID della playlist da cui recuperare le canzoni
     * @return una ResponseEntity che contiene una lista di oggetti CanzoneDto e lo stato HTTP OK
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<CanzoneDto>> getAllCanzoneInPlaylist(@PathVariable Long playlistId){
        List<CanzoneDto> canzoni = playlistCanzoneService.getAllCanzoneInPlaylist(playlistId);
        return ResponseEntity.ok(canzoni);
    }

    /**
     * Gestisce la richiesta di recupero di una playlist con tutte le sue canzoni.
     *
     * @param playlistId l'ID della playlist da recuperare
     * @return una ResponseEntity che contiene l'oggetto PlaylistDto con tutte le sue canzoni e lo stato HTTP OK
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/playlist")
    public ResponseEntity<PlaylistDto> getPlaylistWithCanzoni(@PathVariable Long playlistId){
        PlaylistDto playlistDto = playlistCanzoneService.getPlaylistWithCanzoni(playlistId);
        return ResponseEntity.ok(playlistDto);
    }

}
