package com.example.emotionalsongback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity che rappresenta un Ruolo nel sistema di sicurezza.
 * Mappata alla tabella 'ruoli' nel database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ruoli")
public class Role {

    /**
     * Identificativo univoco del ruolo.
     * Generato automaticamente dal database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome del ruolo.
     * Utilizzato per identificare differenti ruoli (es. USER, ADMIN) nel sistema.
     */
    private String name;
}
