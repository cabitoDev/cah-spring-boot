package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayRequest {
    private String gameId;
    private Long playerId; // Cambié esto a Long para coincidir con el tipo de ID de Player
    private Long cardId;   // Cambié esto a Long para coincidir con el tipo de ID de Card

    public PlayRequest() {}

    public PlayRequest(String gameId, Long playerId, Long cardId) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.cardId = cardId;
    }
}
