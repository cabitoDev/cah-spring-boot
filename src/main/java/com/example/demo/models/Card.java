package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card", schema = "public")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne // Indica que muchas cartas pueden pertenecer a un jugador
    @JoinColumn(name = "player_id", nullable = true) // Esto crea la columna player_id en la tabla card
    @JsonIgnore
    private Player player;

    @ManyToOne // Indica que muchas cartas pueden pertenecer a un juego
    @JoinColumn(name = "game_id", nullable = false) // Esto crea la columna game_id en la tabla card
    @JsonIgnore
    private Game game;

    public Card() {}

    public Card(String text) {
        this.text = text;
    }
}
