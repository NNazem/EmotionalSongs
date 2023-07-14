package com.example.emotionalsongback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CanzoneDto {
    private Long id;
    private String titolo;
    private String autore;
    private String anno;
}
