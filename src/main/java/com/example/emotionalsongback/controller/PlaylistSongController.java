package com.example.emotionalsongback.controller;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.service.PlaylistCanzoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists/{playlistId}/songs")
@AllArgsConstructor
public class PlaylistSongController {

    private PlaylistCanzoneService playlistCanzoneService;

    @PostMapping
    public ResponseEntity<?> addCanzoneToPlaylist(@PathVariable Long playlistId, @RequestBody CanzoneDto canzoneDto){
        playlistCanzoneService.addCanzoneToPlaylist(playlistId, canzoneDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> removeCanzoneFromPlaylist(@PathVariable Long playlistId, @RequestParam("id") Long canzoneId){
        playlistCanzoneService.removeCanzoneFromPlaylist(playlistId, canzoneId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<CanzoneDto>> getAllCanzoneInPlaylist(@PathVariable Long playlistId){
        List<CanzoneDto> canzoni = playlistCanzoneService.getAllCanzoneInPlaylist(playlistId);
        return ResponseEntity.ok(canzoni);
    }

    @GetMapping("/playlist")
    public ResponseEntity<PlaylistDto> getPlaylistWithCanzoni(@PathVariable Long playlistId){
        PlaylistDto playlistDto = playlistCanzoneService.getPlaylistWithCanzoni(playlistId);
        return ResponseEntity.ok(playlistDto);
    }

}
