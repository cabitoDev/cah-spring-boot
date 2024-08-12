package com.example.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game")
@Getter
@Setter
public class Game {

    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Esto permite que las cartas sean parte del juego
    private List<Card> cards;

    // Mapear la lista de jugadores usando una relación OneToMany
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private List<Player> players;

    // Mapear al dueño del juego como una relación OneToOne
    @OneToOne
    @JoinColumn(name = "owner_id")
    private Player owner;

    public Game() {
        this.cards = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public Game(String id, Player player) {
        this.id = id;
        this.cards = new ArrayList<>();
        this.players = new ArrayList<>();
        this.players.add(player);
        this.owner = player;
    }
}
