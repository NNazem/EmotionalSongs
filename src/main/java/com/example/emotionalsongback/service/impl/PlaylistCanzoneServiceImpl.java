package com.example.emotionalsongback.service.impl;

import com.example.emotionalsongback.dto.CanzoneDto;
import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.entity.Canzone;
import com.example.emotionalsongback.entity.Playlist;
import com.example.emotionalsongback.exception.APIException;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.repository.CanzoneRepository;
import com.example.emotionalsongback.repository.PlaylistRepository;
import com.example.emotionalsongback.service.PlaylistCanzoneService;
import com.example.emotionalsongback.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistCanzoneServiceImpl implements PlaylistCanzoneService {

    CanzoneRepository canzoneRepository;

    PlaylistRepository playlistRepository;

    ModelMapper modelMapper;


    @Override
    public void addCanzoneToPlaylist(Long playlistId, CanzoneDto canzoneDto){
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + playlistId));
        Canzone canzone = modelMapper.map(canzoneDto, Canzone.class);
        playlist.getCanzoni().add(canzone);
        playlistRepository.save(playlist);


    }

    @Override
    public void removeCanzoneFromPlaylist(Long playlistId, Long canzoneId){
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + playlistId));
        Canzone canzone = canzoneRepository.findById(canzoneId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + canzoneId));

        if (!playlist.getCanzoni().contains(canzone)) {
            throw new ResourceNotFoundException("Canzone con id: " + canzoneId + " non è associata con la playlist: " + playlistId);
        }

        playlist.getCanzoni().remove(canzone);
        playlistRepository.save(playlist);
    }

    @Override
    public PlaylistDto getPlaylistWithCanzoni(Long playlistId){
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + playlistId));

        PlaylistDto playlistDto = modelMapper.map(playlist, PlaylistDto.class);

        return playlistDto;
    }

    @Override
    public List<CanzoneDto> getAllCanzoneInPlaylist(Long playlistId){
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + playlistId));

        return playlist.getCanzoni().stream().map((canzone -> modelMapper.map(canzone, CanzoneDto.class))).toList();
    }

}
