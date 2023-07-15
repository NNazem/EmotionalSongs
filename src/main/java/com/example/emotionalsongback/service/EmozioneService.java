package com.example.emotionalsongback.service;

import com.example.emotionalsongback.dto.EmozioneDto;
import com.example.emotionalsongback.entity.Emozione;

import java.util.List;
import java.util.Set;

public interface EmozioneService {

    String addEmozione(Long canzoneId, Long playlistId, EmozioneDto emozioneDto);

    Set<Emozione> getEmozioni(Long canzoneId);

    String deleteEmozione(Long canzoneId, Long playlistId ,Long emozioneId);
}
