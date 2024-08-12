package com.example.demo.controllers;
import com.example.demo.models.Game;
import com.example.demo.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "http://localhost:5173/table/cards", "http://localhost:5173" })
@RestController
@RequestMapping("/")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGames() {
        return gameService.getGames();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Object> getGameById(@PathVariable String id) {
        try {
            Optional<Game> game = gameService.getGameById(id);
            if (game.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(game.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game request) {
        try {
            Optional<Game> existingGame = gameService.getGameById(request.getId());

            if (!existingGame.isPresent()) {
                Game newGame = new Game(request.getId(), request.getOwner());
                newGame = gameService.saveGame(newGame);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(newGame);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable String id, @RequestBody Game request) {
        try {
            Optional<Game> existingGame = gameService.getGameById(id);
            if (existingGame.isPresent()) {
                Game updatedGame = gameService.saveGame(request);
                return ResponseEntity.status(HttpStatus.OK).body(updatedGame);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable String id) {
        try {
            gameService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
