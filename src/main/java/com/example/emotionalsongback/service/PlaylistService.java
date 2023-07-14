package com.example.emotionalsongback.service;

import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.entity.Playlist;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PlaylistService {

    PlaylistDto addPlaylist(PlaylistDto playlist);

    PlaylistDto getPlaylist(Long id);

    List<PlaylistDto> getAllPlaylists();

    PlaylistDto updatePlaylist(PlaylistDto playlistDto, Long id);

    void deletePlaylist(Long id);
}