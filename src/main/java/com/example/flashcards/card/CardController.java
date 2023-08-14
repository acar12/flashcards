package com.example.flashcards.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.getById(id);
    }

    @PutMapping("{id}")
    public Card updateCard(@PathVariable Long id, Card newCard) {
        return cardService.updateCard(id, newCard);
    }

    @DeleteMapping("{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
