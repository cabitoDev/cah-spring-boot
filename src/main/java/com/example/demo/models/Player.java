package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "player", schema = "public")
@Getter
@Setter
public class Player {

    @Id
    private String name;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación con Card
    private List<Card> hand;

    @ManyToOne
    @JoinColumn(name = "game_id") // Relación con Game
    @JsonIgnore
    private Game game;

    public Player() {
        this.hand = new ArrayList<>(); // Inicializar la mano como una lista vacía
    }

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>(); // Inicializar la mano como una lista vacía
    }
}
