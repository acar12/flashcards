package com.example.flashcards.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configuration
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public Card getById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("card id of " + id + " doesn't exist"));
    }

    public Card createCard(Card card) {
        if (card.getFront().isEmpty() || card.getBack().isEmpty())
            throw new IllegalArgumentException("card front or back is empty string");
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card newCard) {
        Card card = getById(id);
        card.setFront(newCard.getFront());
        card.setBack(newCard.getBack());
        return card;
    }

    public void deleteCard(Long id) {
        Card card = getById(id);
        cardRepository.delete(card);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            cardRepository.saveAll(List.of(
                    new Card("Foo", "Bar"),
                    new Card("Abc", "Def")
            ));
        };
    }
}
