package com.example.demo.controllers;

import com.example.demo.models.Game;
import com.example.demo.models.PlayRequest;
import com.example.demo.models.Player;
import com.example.demo.services.BlackCardService;
import com.example.demo.services.GameService;
import com.example.demo.services.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketGameController {

    private final GameService gameService;
    private final BlackCardService blackCardService;
    private final PlayerService playerService;

    @Autowired
    public WebSocketGameController(GameService gameService, BlackCardService blackCardService, PlayerService playerService) {
        this.gameService = gameService;
        this.blackCardService = blackCardService;
        this.playerService = playerService;
    }

    @MessageMapping("/startGame/{gameId}")
    @SendTo("/topic/updatedGame/{gameId}")
    public Game broadcastMessage(@Payload Game game, @org.springframework.web.bind.annotation.PathVariable String gameId) {
        game.setBlackCards(this.blackCardService.getBlackCards());
        gameService.dealCards(game);
        game.setRound(1);
        game.setState("READING");
        return game;
    }

    @MessageMapping("/updateGameState/{gameId}")
    @SendTo("/topic/updatedGameState/{gameId}")
    public String broadcastGameState(@Payload String state, @org.springframework.web.bind.annotation.PathVariable String gameId) {
        // Aquí puedes utilizar la gameId si es necesario
        return state;
    }

    @MessageMapping("/addPlayer/{gameId}")
    @SendTo("/topic/addedPlayer/{gameId}")
    public Player broadcastPlayer(@Payload String player, @org.springframework.web.bind.annotation.PathVariable String gameId) {
        return playerService.getPlayerByName(player);
    }
}

