package com.example.emotionalsongback.service;

import com.example.emotionalsongback.dto.EmozioneDto;
import com.example.emotionalsongback.entity.Emozione;

public interface EmozioneService {

    String addEmozione(Long canzoneId, EmozioneDto emozioneDto);
}
