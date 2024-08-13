package com.example.demo.controllers;

import com.example.demo.models.Game;
import com.example.demo.models.PlayRequest;
import com.example.demo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketGameController {

    private final GameService gameService;

    @Autowired
    public WebSocketGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/startGame/{gameId}")
    @SendTo("/topic/updatedGame/{gameId}")
    public Game broadcastMessage(@Payload Game game, @org.springframework.web.bind.annotation.PathVariable String gameId) {
        // Aquí puedes utilizar la gameId si es necesario
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
    public String broadcastPlayer(@Payload String player, @org.springframework.web.bind.annotation.PathVariable String gameId) {
        // Aquí puedes utilizar la gameId si es necesario
        return player;
    }
}

