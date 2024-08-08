package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Game;
import com.example.demo.repositories.GameRepository;

@Service
public class GameService {

    private GameRepository gameRepo;

    @Autowired
    public GameService(GameRepository gameRepo) {
        this.gameRepo = gameRepo;

    }

    public List<Game> getGames() {
        return gameRepo.findAll();
    }

    public Optional<Game> getGameById(String id) {
        return gameRepo.findById(id);
    }

    public void deleteById(String id) {
        gameRepo.deleteById(id);
    }

    public Game saveGame(Game game) {
        return gameRepo.save(game);
    }

}
