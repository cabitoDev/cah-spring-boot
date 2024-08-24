package com.example.demo.services;

import com.example.demo.models.Game;
import com.example.demo.models.Card;
import com.example.demo.models.Player;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    // Obtener todos los juegos
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    // Obtener un juego por ID
    public Optional<Game> getGameById(String id) {
        return gameRepository.findById(id);
    }

    // Guardar un nuevo juego
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    // Repartir cartas a los jugadores
    public Game dealCards(Game game) {
        // Lógica para repartir cartas
        List<Player> players = game.getPlayers();
        int numCardsToDeal = 7; // Cambia esto según tus reglas

        // Supongamos que tienes un método que baraja y reparte cartas
        List<Card> deck = new ArrayList<>(game.getWhiteCards());
        for (Player player : players) {
            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < numCardsToDeal && !deck.isEmpty(); i++) {
                Card card = deck.remove(0);
                hand.add(card);
            }
            player.setHand(hand);
        }

        return game;
    }

    // Jugar una carta
    public Game playCard(String gameId, String playerId, Long cardId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found")); // Obtener el juego

        // Encuentra el jugador
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        // Lógica para manejar la jugada de una carta
        // Eliminar la carta de la mano del jugador
        player.getHand().removeIf(card -> card.getId().equals(cardId)); // Asegúrate de que el método getId existe en Card

        // Actualiza el jugador
        playerRepository.save(player);

        // Aquí puedes agregar la carta a la pila de jugadas, dependiendo de las reglas del juego

        return gameRepository.save(game); // Actualiza el estado del juego
    }

    // Seleccionar un ganador
    public Game selectWinner(String gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found")); // Obtener el juego

        // Lógica para seleccionar un ganador
        // Por ejemplo, determinar el jugador con la mejor carta o el que ha ganado más rondas

        // Aquí deberías implementar la lógica según las reglas de tu juego

        return gameRepository.save(game); // Actualiza el estado del juego
    }

    // Eliminar un juego por ID
    public void deleteById(String id) {
        gameRepository.deleteById(id);
    }

    public Game joinGame(String gameId, Player player) {
        // Buscar el juego en la base de datos
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
    
            // Asegurarse de que la lista de jugadores no sea null
            if (game.getPlayers() == null) {
                game.setPlayers(new ArrayList<>()); // Inicializar si es null
            }
    
            // Asegurarse de que el jugador no esté ya en el juego
            if (!game.getPlayers().contains(player)) {
                // Agregar el nuevo jugador a la lista de jugadores
                game.getPlayers().add(player);
    
                // Establece el juego para el jugador
                player.setGame(game); // Asigna el juego al jugador
                
                // Guarda el jugador en la base de datos
                playerRepository.save(player); // Guarda el jugador
    
                // Guarda el juego en la base de datos
                return gameRepository.save(game); // Actualiza el juego
            } else {
                // El jugador ya está en el juego
                throw new IllegalArgumentException("Player already in the game");
            }
        } else {
            throw new IllegalArgumentException("Game not found");
        }
    }
    

    public Player savePlayer(String playerName) {
        return playerRepository.save(new Player(playerName));
    }
}
