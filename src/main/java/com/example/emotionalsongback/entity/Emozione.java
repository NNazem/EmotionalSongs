package com.example.emotionalsongback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;


/**
 * Entity rappresentante un'Emozione nel database.
 * Mappata alla tabella 'emozioni'.
 */
@Entity
@Table(name = "emozioni", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "idUtente"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emozione implements Serializable {

    /**
     * Identificativo univoco dell'emozione.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUtente;

    /**
     * Tipo di emozione.
     * È un valore enumerato che rappresenta le varie tipologie di emozioni.
     */
    @Enumerated(EnumType.STRING)
    private TipoEmozione tipoEmozione;

    /**
     * Voto associato all'emozione.
     * Può essere utilizzato per quantificare l'emozione.
     */
    private String voto;

    /**
     * Descrizione dettagliata dell'emozione da parte dell'utente.
     */
    private String descrizione;

    /**
     * Enumerazione che definisce i tipi di emozioni disponibili.
     */
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