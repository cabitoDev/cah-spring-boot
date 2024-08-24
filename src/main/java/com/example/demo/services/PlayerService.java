package com.example.demo.services;

import com.example.demo.models.Player;
import com.example.demo.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Player savePlayer(String text) {
        Player p = new Player(text);
        this.playerRepository.save(p);
        return p;
    }

    public Player getPlayerByName(String name) {
        try{
            return playerRepository.findById(name).get();
} catch (Exception e) {
            return null;
        }
    }

}