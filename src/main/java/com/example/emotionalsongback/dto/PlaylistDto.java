package com.example.emotionalsongback.dto;

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
    private int numeroCanzoni;

}
