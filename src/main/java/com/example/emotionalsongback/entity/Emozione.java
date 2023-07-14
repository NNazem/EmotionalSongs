package com.example.emotionalsongback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Entity
@Table(name = "emozioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emozione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoEmozione tipoEmozione;
    private String voto;
    private String descrizione;

    public enum TipoEmozione {
        STUPORE,
        TENEREZZA,
        SOLLENITA,
        NOSTALGIA,
        CALMA,
        POTENZA,
        GIOIA,
        NERVOSISMO,
        TRISTEZZA
    }
}