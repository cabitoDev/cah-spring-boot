package com.example.demo.controllers;

import com.example.demo.models.Card;
import com.example.demo.models.Game;
import com.example.demo.models.Player;
import com.example.demo.services.BlackCardService;
import com.example.demo.services.GameService;
import com.example.demo.services.PlayerService;

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
    private final BlackCardService blackCardService;
    private final PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, BlackCardService blackCardService, PlayerService playerService) {
        this.gameService = gameService;
        this.blackCardService = blackCardService;
        this.playerService = playerService;
    }

    @GetMapping
    public List<Game> getGames() {
        return gameService.getGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGameById(@PathVariable String id) {
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
            String ownerName = request.getOwner().getName();
            Player owner = gameService.savePlayer(ownerName);
            Game newGame = new Game(request.getId(), owner, this.blackCardService.getBlackCards());

            newGame = gameService.saveGame(newGame);
            return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
        } catch (Exception e) {
            e.printStackTrace(); // Muestra el stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable String id, @RequestBody Game request) {
        try {
            Optional<Game> existingGameOptional = gameService.getGameById(id);
            if (existingGameOptional.isPresent()) {
                Game existingGame = existingGameOptional.get();

                // Actualiza los detalles del juego
                existingGame.setOwner(request.getOwner());
                existingGame.setPlayers(request.getPlayers());

                // Actualiza las cartas y establece la relación con el juego
                List<Card> updatedCards = request.getWhiteCards();
                for (Card card : updatedCards) {
                    card.setGame(existingGame); // Establece la referencia al juego
                }
                existingGame.setWhiteCards(updatedCards); // Establece las cartas actualizadas

                Game updatedGame = gameService.saveGame(existingGame); // Guarda el juego actualizado
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

    @PostMapping("/join/{gameId}")
    public ResponseEntity<Game> existGame(@PathVariable String gameId,  @RequestBody Player player) {
        try {
            Optional<Game> existingGameOptional = gameService.getGameById(gameId);
            if (existingGameOptional.isPresent()) {
                String playerName = player.getName();
                if(playerService.getPlayerByName(playerName) == null){
                    gameService.savePlayer(playerName);
                }
                return ResponseEntity.status(HttpStatus.OK).body(existingGameOptional.get());
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
