package com.example.emotionalsongback.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "canzoni")
public class Canzone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String autore;
    @Column(nullable = false)
    private String anno;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "canzoni_emozioni",
            joinColumns = @JoinColumn(name = "canzone_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "emozioni_id", referencedColumnName = "id")
    )
    private Set<Emozione> emozioni = new HashSet<>();
}
