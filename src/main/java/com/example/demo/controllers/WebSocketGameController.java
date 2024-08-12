package com.example.demo.controllers;

import com.example.demo.models.Game;
import com.example.demo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class WebSocketGameController {

    private final GameService gameService;

    @Autowired
    public WebSocketGameController(GameService gameService) {
        this.gameService = gameService;
    }

    // Maneja los mensajes enviados al servidor desde el cliente.
    @MessageMapping("/updateGame")
    @SendTo("/topic/gameUpdates")
    public Game updateGame(Game game) throws Exception {
        // Actualiza el juego en la base de datos.
        Game updatedGame = gameService.saveGame(game);
        return updatedGame; // El juego actualizado se enviará a todos los clientes suscritos.
    }

    // Puedes añadir más métodos para manejar otros tipos de mensajes.
}
