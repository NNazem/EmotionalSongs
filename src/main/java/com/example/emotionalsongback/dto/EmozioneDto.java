package com.example.emotionalsongback.dto;

import com.example.emotionalsongback.entity.Emozione;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmozioneDto {
    private Long id;
    private Emozione.TipoEmozione tipoEmozione;
    private String voto;
    private String descrizione;

}
