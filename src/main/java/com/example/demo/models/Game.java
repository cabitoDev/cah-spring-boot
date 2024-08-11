package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "game", schema = "public")
@Getter
@Setter
public class Game {

	@Id
	private String id;

	private List<String> cards;
	private List<Player> players;
	private Player owner;

	public Game() {
		this.cards = new ArrayList<>();
	}

	public Game(String id, Player player) {
		this.id = id;
		this.cards = new ArrayList<>();
		this.players = new ArrayList<>();
		this.players.add(player);
		this.owner = player;
	}
}
