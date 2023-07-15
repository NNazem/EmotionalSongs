package com.example.emotionalsongback.service.impl;

import com.example.emotionalsongback.dto.EmozioneDto;
import com.example.emotionalsongback.entity.Canzone;
import com.example.emotionalsongback.entity.Emozione;
import com.example.emotionalsongback.entity.Playlist;
import com.example.emotionalsongback.entity.Utente;
import com.example.emotionalsongback.exception.APIException;
import com.example.emotionalsongback.repository.CanzoneRepository;
import com.example.emotionalsongback.repository.PlaylistRepository;
import com.example.emotionalsongback.service.AuthService;
import com.example.emotionalsongback.service.EmozioneService;
import com.example.emotionalsongback.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmozioneServiceImpl implements EmozioneService {

    private CanzoneRepository canzoneRepository;
    private ModelMapper modelMapper;
    private AuthService authService;
    private PlaylistRepository playlistRepository;

    @Override
    public String addEmozione(Long canzoneId, Long playlistId, EmozioneDto emozioneDto) {

        Utente utente = authService.getUtenteFromAuthentication();
        Set<Playlist> playlists = utente.getPlaylists();

        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + playlistId)
        );

        Canzone canzone = canzoneRepository.findById(canzoneId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + canzoneId));

        if (!playlists.contains(playlist)) {
            throw new APIException(HttpStatus.NOT_FOUND, "Playlist con l'id: " + playlistId + " non associata all'utente con id: " + utente.getId());
        }

        if(!playlist.getCanzoni().contains(canzone)){
            throw new APIException(HttpStatus.NOT_FOUND, "Playlist con l'id: " + playlistId + " non contiene la canzone con id: " + canzoneId);
        }

        Emozione emozione = new Emozione();
        emozione.setTipoEmozione(emozioneDto.getTipoEmozione());
        emozione.setVoto(emozioneDto.getVoto());
        emozione.setDescrizione(emozioneDto.getDescrizione());

        Set<Emozione> emozioni = canzone.getEmozioni();

        boolean emozionePresente = emozioni.stream()
                .anyMatch(e -> e.getTipoEmozione() == emozione.getTipoEmozione());

        if (emozionePresente) {
            throw new APIException(HttpStatus.CONFLICT, "L'emozione " + emozione.getTipoEmozione() + " è già associata alla canzone con id: " + canzoneId);
        }

        emozioni.add(emozione);
        canzoneRepository.save(canzone);

        return "Emozione " + emozione.getTipoEmozione() + " associata correttamente.";
    }

    @Override
    public Set<Emozione> getEmozioni(Long canzoneId) {
        Canzone canzone = canzoneRepository.findById(canzoneId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + canzoneId));

        Set<Emozione> emozioni = canzone.getEmozioni();

        if (emozioni.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Non vi sono emozioni associate alla canzone con id: " + canzoneId);
        }

        return emozioni;
    }

    @Override
    public String deleteEmozione(Long canzoneId, Long playlistId, Long emozioneId) {

        Utente utente = authService.getUtenteFromAuthentication();
        Set<Playlist> playlists = utente.getPlaylists();

        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Playlist non trovate con l'id: " + playlistId)
        );

        Canzone canzone = canzoneRepository.findById(canzoneId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + canzoneId));

        if (!playlists.contains(playlist)) {
            throw new APIException(HttpStatus.NOT_FOUND, "Playlist con l'id: " + playlistId + " non associata all'utente con id: " + utente.getId());
        }

        if(!playlist.getCanzoni().contains(canzone)){
            throw new APIException(HttpStatus.NOT_FOUND, "Playlist con l'id: " + playlistId + " non contiene la canzone con id: " + canzoneId);
        }

        Set<Emozione> emozioni = canzone.getEmozioni();

        boolean emozioneRimossa = emozioni.stream()
                .filter(e -> e.getId() == emozioneId)
                .findFirst()
                .map(emozioneTrovata -> {
                    emozioni.remove(emozioneTrovata);
                    canzoneRepository.save(canzone);
                    return true;
                }).orElse(false);

        if(emozioneRimossa){
            return "Emozione rimossa correttamente.";
        }else{
            throw new APIException(HttpStatus.NOT_FOUND, "L'emozione non è associata alla canzone: " + canzoneId);
        }

    }


}
