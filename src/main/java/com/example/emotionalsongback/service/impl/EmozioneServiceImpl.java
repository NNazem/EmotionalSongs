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

import java.util.*;
import java.util.stream.Collectors;

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

        if (!playlist.getCanzoni().contains(canzone)) {
            throw new APIException(HttpStatus.NOT_FOUND, "Playlist con l'id: " + playlistId + " non contiene la canzone con id: " + canzoneId);
        }

        Emozione emozione = new Emozione();
        emozione.setIdUtente(utente.getId());
        emozione.setTipoEmozione(emozioneDto.getTipoEmozione());
        emozione.setVoto(emozioneDto.getVoto());
        emozione.setDescrizione(emozioneDto.getDescrizione());

        Set<Emozione> emozioni = canzone.getEmozioni();

        Optional<Emozione> existingEmozione = emozioni.stream()
                .filter(e -> e.getTipoEmozione() == emozione.getTipoEmozione() && e.getIdUtente() == utente.getId())
                .findFirst();

        if (existingEmozione.isPresent()) {
            Emozione emozioneToUpdate = existingEmozione.get();
            emozioneToUpdate.setVoto(emozioneDto.getVoto());
            emozioneToUpdate.setDescrizione(emozioneDto.getDescrizione());
        } else {
            emozioni.add(emozione);
        }

        canzoneRepository.save(canzone);

        return "Emozione " + emozione.getTipoEmozione() + " associata correttamente.";
    }

    @Override
    public Map<Emozione.TipoEmozione, Record> getEmozioni(Long canzoneId) {

        Utente utente = authService.getUtenteFromAuthentication();

        Canzone canzone = canzoneRepository.findById(canzoneId).orElseThrow(
                () -> new APIException(HttpStatus.NOT_FOUND, "Canzone non trovata con id: " + canzoneId));

        Set<Emozione> emozioni = canzone.getEmozioni();


        if (emozioni.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Non vi sono emozioni associate alla canzone con id: " + canzoneId);
        }

        record votoEmozione(Emozione.TipoEmozione tipoEmozione, double media, double voto, String commentoUtente, List<String> commentiUtenti) {
        }
        ;

        Map<Emozione.TipoEmozione, Double> votiGlobali = emozioni.stream().
                collect(Collectors.groupingBy(Emozione::getTipoEmozione,
                        Collectors.averagingInt(e -> Integer.parseInt(e.getVoto()))));


        Map<Emozione.TipoEmozione, Record> votiUtenteConMedia = votiGlobali.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            Emozione.TipoEmozione tipo = entry.getKey();
                            Double mediaVoti = entry.getValue();


                            Optional<Emozione> emozioneUtente = Optional.empty();

                            if (utente != null) {
                                emozioneUtente = emozioni.stream()
                                        .filter(e -> e.getIdUtente().equals(utente.getId()) && e.getTipoEmozione().equals(tipo))
                                        .findFirst();
                            }

                            Double votoUtente;
                            String commentoUtente;

                            if (emozioneUtente.isPresent()) {
                                votoUtente = Double.valueOf(emozioneUtente.get().getVoto());
                                commentoUtente = emozioneUtente.get().getDescrizione();
                            } else {
                                votoUtente = -1.0;
                                commentoUtente = "";
                            }

                            List<String> commentiUtenti = emozioni.stream().
                                    filter(e ->   e.getTipoEmozione().equals(tipo) && (utente == null || !e.getIdUtente().equals(utente.getId())))
                                    .map(Emozione::getDescrizione)
                                    .collect(Collectors.toList());


                            return new votoEmozione(tipo,mediaVoti, votoUtente, commentoUtente, commentiUtenti);

                        }
                ));

        return votiUtenteConMedia;


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

        if (!playlist.getCanzoni().contains(canzone)) {
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

        if (emozioneRimossa) {
            return "Emozione rimossa correttamente.";
        } else {
            throw new APIException(HttpStatus.NOT_FOUND, "L'emozione non è associata alla canzone: " + canzoneId);
        }

    }


}
