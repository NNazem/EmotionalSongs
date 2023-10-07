package com.example.emotionalsongback.service.impl;

import com.example.emotionalsongback.dto.PlaylistDto;
import com.example.emotionalsongback.entity.Playlist;
import com.example.emotionalsongback.entity.Utente;
import com.example.emotionalsongback.exception.APIException;
import com.example.emotionalsongback.exception.ResourceNotFoundException;
import com.example.emotionalsongback.repository.PlaylistRepository;
import com.example.emotionalsongback.repository.UtenteRepository;
import com.example.emotionalsongback.service.AuthService;
import com.example.emotionalsongback.service.PlaylistService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistRepository playlistRepository;
    private AuthService authService;
    private UtenteRepository utenteRepository;

    private ModelMapper modelMapper;

    @Override
    public PlaylistDto addPlaylist(PlaylistDto playlistDto) {

        Playlist playlist = modelMapper.map(playlistDto, Playlist.class);

        Playlist savedPlaylist = playlistRepository.save(playlist);

        Utente utente = authService.getUtenteFromAuthentication();
        Set<Playlist> playlistsUtente = utente.getPlaylists();
        playlistsUtente.add(savedPlaylist);
        utenteRepository.save(utente);

        PlaylistDto savedPlaylistDto = modelMapper.map(savedPlaylist, PlaylistDto.class);

        return savedPlaylistDto;
    }

    @Override
    public PlaylistDto getPlaylist(Long id) {

        Utente utente = authService.getUtenteFromAuthentication();
        Set<Playlist> playlists = utente.getPlaylists();
        Playlist playlist = playlistRepository.findById(id).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + id));

        if(!playlists.contains(playlist)){
            throw new APIException(HttpStatus.UNAUTHORIZED, "Playlist con l'id: " + id +" non associata con l'utente: " + utente.getUsername());
        }

        return modelMapper.map(playlist, PlaylistDto.class);
    }

    @Override
    public List<PlaylistDto> getAllPlaylists() {


        Utente utente = authService.getUtenteFromAuthentication();


        Set<Playlist> playlists = utente.getPlaylists();
        return playlists.stream().map((playlist -> modelMapper.map(playlist, PlaylistDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public PlaylistDto updatePlaylist(PlaylistDto playlistDto, Long id){

        Utente utente = authService.getUtenteFromAuthentication();
        Set<Playlist> playlists = utente.getPlaylists();
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(
                        () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + id));

        if(!playlists.contains(playlist)){
            throw new APIException(HttpStatus.UNAUTHORIZED, "Playlist con l'id: " + id +" non associata con l'utente: " + utente.getUsername());
        }

        playlist.setName(playlistDto.getName());

        Playlist playlistUpdated = playlistRepository.save(playlist);

        return modelMapper.map(playlistUpdated, PlaylistDto.class);
    }

    @Override
    public void deletePlaylist(Long id){

        Utente utente = authService.getUtenteFromAuthentication();
        Set<Playlist> playlists = utente.getPlaylists();

        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(
                        () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + id));

        if(!playlists.contains(playlist)){
            throw new APIException(HttpStatus.UNAUTHORIZED, "Playlist con l'id: " + id +" non associata con l'utente: " + utente.getUsername());
        }

        playlists.remove(playlist);

        playlistRepository.deleteById(id);
    }
}
