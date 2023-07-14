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

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<PlaylistDto> playlistService(@RequestBody PlaylistDto playlistDto){
        PlaylistDto savedPlaylist = playlistService.addPlaylist(playlistDto);
        return new ResponseEntity<>(savedPlaylist, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<PlaylistDto> getPlaylist(@PathVariable Long id){
        PlaylistDto playlistDto = playlistService.getPlaylist(id);
        return ResponseEntity.ok(playlistDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<PlaylistDto>> getPlaylist(){
        List<PlaylistDto> playlistDto = playlistService.getAllPlaylists();
        return ResponseEntity.ok(playlistDto);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<PlaylistDto> getPlaylist(@RequestBody PlaylistDto playlistDto, @PathVariable Long id){
        PlaylistDto playlistDtoUpdate = playlistService.updatePlaylist(playlistDto,id);
        return ResponseEntity.ok(playlistDtoUpdate);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        playlistService.deletePlaylist(id);
        return ResponseEntity.ok("Playlist eliminata con successo");
    }
}
