package com.example.demo.services;

import com.example.demo.models.BlackCard;
import com.example.demo.repositories.BlackCardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackCardService {

    private final BlackCardRepository blackCardRepository;

    @Autowired
    public BlackCardService(BlackCardRepository blackCardRepository) {
        this.blackCardRepository = blackCardRepository;
    }

    public List<BlackCard> getBlackCards() {
        return blackCardRepository.findAll();
    }

    public BlackCard saveBlackCard(String text) {
        BlackCard blackCard = new BlackCard(text);
        this.blackCardRepository.save(blackCard);
        return blackCard;
    }

}