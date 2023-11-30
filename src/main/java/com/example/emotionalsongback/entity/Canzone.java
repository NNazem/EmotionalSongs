package com.example.emotionalsongback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity rappresentante una Canzone nel database.
 * Mappata alla tabella 'canzoni' con relazioni Many-To-Many verso 'Emozione'.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "canzoni")
public class Canzone {

    /**
     * Identificativo univoco della canzone.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titolo della canzone.
     * Non può essere null.
     */
    @Column(nullable = false)
    private String titolo;

    /**
     * Autore della canzone.
     * Non può essere null.
     */
    @Column(nullable = false)
    private String autore;

    /**
     * Anno di pubblicazione della canzone.
     * Non può essere null.
     */
    @Column(nullable = false)
    private String anno;

    /**
     * Insieme di emozioni associate alla canzone.
     * Inizializzato come un HashSet vuoto.
     * Gestisce una relazione Many-To-Many con la tabella 'emozioni'.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "canzoni_emozioni",
            joinColumns = @JoinColumn(name = "canzone_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "emozioni_id", referencedColumnName = "id")
    )
    private Set<Emozione> emozioni = new HashSet<>();
}
