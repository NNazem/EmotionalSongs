package com.example.emotionalsongback.controller;


import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.entity.Playlist;
import com.example.emotionalsongback.entity.Utente;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.service.PlaylistService;
import com.example.emotionalsongback.service.impl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/playlists")
@AllArgsConstructor
public class PlaylistController {

    private PlaylistService playlistService;
    private AuthServiceImpl authService;

    /**
     * Gestisce la richiesta POST per creare una nuova playlist.
     *
     * @param playlistDto l'oggetto PlaylistDto che rappresenta la playlist da creare
     * @return una ResponseEntity che contiene l'oggetto PlaylistDto creato e lo stato HTTP CREATED
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<PlaylistDto> playlistService(@RequestBody PlaylistDto playlistDto){
        PlaylistDto savedPlaylist = playlistService.addPlaylist(playlistDto);
        return new ResponseEntity<>(savedPlaylist, HttpStatus.CREATED);
    }

    /**
     * Gestisce la richiesta GET per ottenere una playlist tramite ID.
     *
     * @param id l'ID della playlist da ottenere
     * @return una ResponseEntity che contiene l'oggetto PlaylistDto recuperato e lo stato HTTP OK
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<PlaylistDto> getPlaylist(@PathVariable Long id){
        PlaylistDto playlistDto = playlistService.getPlaylist(id);
        return ResponseEntity.ok(playlistDto);
    }

    /**
     * Gestisce la richiesta GET per ottenere tutte le playlist.
     *
     * @return una ResponseEntity che contiene una lista di oggetti PlaylistDto e lo stato HTTP 200 OK
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<PlaylistDto>> getAllPlaylist(){
        List<PlaylistDto> playlistDto = playlistService.getAllPlaylists();
        return ResponseEntity.ok(playlistDto);
    }

    /**
     * Gestisce la richiesta PUT per modificare una playlist tramite ID.
     *
     * @param playlistDto l'oggetto PlaylistDto che rappresenta la playlist modificata
     * @param id l'ID della playlist da modificare
     * @return una ResponseEntity che contiene l'oggetto PlaylistDto modificato e lo stato HTTP OK
     */
    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<PlaylistDto> getPlaylist(@RequestBody PlaylistDto playlistDto, @PathVariable Long id){
        PlaylistDto playlistDtoUpdate = playlistService.updatePlaylist(playlistDto,id);
        return ResponseEntity.ok(playlistDtoUpdate);
    }

    /**
     * Gestisce la richiesta DELETE di eliminazione di una playlist per ID.
     *
     * @param id l'ID della playlist da eliminare
     * @return una ResponseEntity che contiene un messaggio di conferma e lo stato HTTP OK
     */
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long id){
        playlistService.deletePlaylist(id);
        return ResponseEntity.ok("Playlist eliminata con successo");
    }
}
