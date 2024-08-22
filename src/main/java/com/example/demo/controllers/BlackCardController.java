package com.example.demo.controllers;

import com.example.demo.models.BlackCard;
import com.example.demo.services.BlackCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = { "http://localhost:5173/table/cards", "http://localhost:5173" })
@RestController
@RequestMapping("/blackCards")
public class BlackCardController {

    private final BlackCardService blackCardService;

    @Autowired
    public BlackCardController(BlackCardService blackCardService) {
        this.blackCardService = blackCardService;
    }

    @GetMapping
    public List<BlackCard> getGames() {
        return blackCardService.getBlackCards();
    }
    @PostMapping
    public ResponseEntity<BlackCard> createBlackCard(@RequestBody String text) {
        try {
                this.blackCardService.saveBlackCard(text);
                return ResponseEntity.status(HttpStatus.OK).body(null); 
        } catch (Exception e) {
            e.printStackTrace(); // Muestra el stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
   
}
