package com.example.emotionalsongback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Rappresenta un utente nel sistema. Ogni utente ha attributi personali
 * e relazioni con playlist e ruoli.
 * Mappata alla tabella "utenti" in un database.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utenti")
public class Utente {

    /**
     * Identificativo unico per ogni utente. Viene generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome dell'utente. Campo obbligatorio.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * Cognome dell'utente. Campo obbligatorio.
     */
    @Column(nullable = false)
    private String cognome;

    /**
     * Indirizzo dell'utente. Campo obbligatorio.
     */
    @Column(nullable = false)
    private String indirizzo;

    /**
     * Indirizzo email dell'utente. Deve essere unico nel sistema. Campo obbligatorio.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Username per l'accesso dell'utente. Deve essere unico nel sistema. Campo obbligatorio.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Codice fiscale dell'utente. Campo obbligatorio.
     */
    @Column(nullable = false)
    private String codiceFiscale;

    /**
     * Password per l'accesso dell'utente. Campo obbligatorio.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Insieme delle playlist associate all'utente. Viene caricato con strategia EAGER.
     * Ogni utente può avere multiple playlist.
     *
     * La strategia EAGER indica che i dati correlati vengono caricati immediatamente
     * insieme all'entità principale. Questo significa che, quando si carica un'entità,
     * tutte le sue entità correlate vengono caricate simultaneamente dal database.
     */

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "utenti_playlist",
            joinColumns = @JoinColumn(name = "utenti_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"))
    private Set<Playlist> playlists;

    /**
     * Insieme dei ruoli associati all'utente. Viene caricato con strategia EAGER.
     * Un utente può avere più ruoli.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "utenti_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
