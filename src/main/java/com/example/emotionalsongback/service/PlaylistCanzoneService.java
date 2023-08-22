package com.example.emotionalsongback.service;


import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.exception.ResourceNotFoundException;

import java.util.List;

public interface PlaylistCanzoneService {

    void addCanzoneToPlaylist(Long playlistId, Long canzoneId);

    void removeCanzoneFromPlaylist(Long playlistId, Long canzoneId);

    //void addPlaylistToCurrentUser(Long playlistId);

    PlaylistDto getPlaylistWithCanzoni(Long playlistId);

    List<CanzoneDto> getAllCanzoneInPlaylist(Long playlistId);

    //List<CanzoneDto> getCanzoniInPlaylistOfCurrentUser(Long playlistId);

}
