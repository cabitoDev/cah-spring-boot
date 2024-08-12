package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único de la carta
    private String text; // Texto de la carta (asumiendo que "text" es lo que deseas guardar)

    // Constructor vacío
    public Card() {}

    // Constructor con texto
    public Card(String text) {
        this.text = text;
    }
}
