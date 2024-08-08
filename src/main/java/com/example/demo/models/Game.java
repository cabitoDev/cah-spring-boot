package com.example.demo.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

	private String[] cards;


	public Game() {
	}

	public Game(String id) {
		this.id = id;
		this.cards = new ArrayList();
	}

}