package com.example.flashcards.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card getById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("card id of " + id + " doesn't exist"));
    }

    public Card updateCard(Long id, Card newCard) {
        if (newCard.getFront().isBlank() || newCard.getBack().isBlank())
            throw new IllegalArgumentException("cannot update front and/or back fields with blank strings");
        Card card = getById(id);
        card.setFront(newCard.getFront());
        card.setBack(newCard.getBack());
        return card;
    }

    public void deleteCard(Long id) {
        Card card = getById(id);
        cardRepository.delete(card);
    }
}
