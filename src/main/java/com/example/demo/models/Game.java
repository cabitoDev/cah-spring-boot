package com.example.demo.models;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación con Card
    private List<Card> cards;
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación con Card
    private List<BlackCard> blackCards;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación con Player
    private List<Player> players;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Player owner;

    private String state;
    private int round;
    private int roundsToWind;

    public Game() {
        this.cards = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public Game(String id, Player player, List<BlackCard> blackCards) {
        this.id = id;
        this.cards = new ArrayList<>();
        this.players = new ArrayList<>();
        this.players.add(player);
        this.owner = player;
        this.state = "WAITING";
        this.round = 0;
        this.blackCards = blackCards;
    }
}
