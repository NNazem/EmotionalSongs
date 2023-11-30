package com.example.emotionalsongback.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity che rappresenta una Playlist nel database.
 * Mappata alla tabella 'playlists' con una relazione Many-To-Many con 'Canzone'.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlists")
public class Playlist {

    /**
     * Identificativo univoco della playlist.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome della playlist.
     * Non può essere null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Insieme di canzoni presenti nella playlist.
     * Inizializzato come un HashSet vuoto.
     * Gestisce una relazione Many-To-Many con la tabella 'canzoni'.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "playlist_canzoni",
            joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "canzone_id", referencedColumnName = "id")
    )
    private Set<Canzone> canzoni = new HashSet<>();

    /**
     * Numero di canzoni presenti nella playlist.
     * Calcolato in base al set di canzoni.
     */
    private int numeroCanzoni;
}
