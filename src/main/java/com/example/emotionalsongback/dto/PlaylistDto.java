package com.example.emotionalsongback.dto;

import com.example.emotionalsongback.entity.Canzone;
import com.example.emotionalsongback.entity.Utente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {
    private Long id;
    private String name;
    private Set<CanzoneDto> canzoni = new HashSet<>();

}
