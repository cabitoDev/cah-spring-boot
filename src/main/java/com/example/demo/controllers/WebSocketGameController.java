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

    // Maneja la solicitud para repartir cartas a los jugadores
    @MessageMapping("/dealCards")
    @SendTo("/topic/gameUpdates")
    public Game dealCards(Game game) throws Exception {
        // Lógica para repartir cartas entre los jugadores
        Game updatedGame = gameService.dealCards(game); // Asegúrate de que este método esté implementado correctamente
        return updatedGame; // Envía el estado del juego actualizado a todos los clientes
    }

    // Maneja la solicitud para jugar una carta
    @MessageMapping("/playCard")
    @SendTo("/topic/gameUpdates")
    public Game playCard(PlayRequest playRequest) throws Exception {
        // Lógica para manejar el juego de la carta
        Game updatedGame = gameService.playCard(playRequest.getGameId(), playRequest.getPlayerId(), playRequest.getCardId());
        return updatedGame; // Envía el estado del juego actualizado a todos los clientes
    }

    // Maneja la solicitud para seleccionar un ganador
    @MessageMapping("/selectWinner")
    @SendTo("/topic/gameUpdates")
    public Game selectWinner(String gameId) throws Exception {
        // Lógica para seleccionar un ganador
        Game updatedGame = gameService.selectWinner(gameId);
        return updatedGame; // Envía el estado del juego actualizado a todos los clientes
    }

    @MessageMapping("/broadcast")
@SendTo("/topic/reply")
public String broadcastMessage(@Payload String message) {
  return "You have received a message: " + message;
}
}
