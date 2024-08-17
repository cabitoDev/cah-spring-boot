package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "blackCard", schema = "public")
@Getter
@Setter
public class BlackCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;


    public BlackCard() {}

    public BlackCard(String text) {
        this.text = text;
    }
}
