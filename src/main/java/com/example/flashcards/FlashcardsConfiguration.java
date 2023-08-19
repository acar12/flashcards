package com.example.flashcards;

import com.example.flashcards.card.Card;
import com.example.flashcards.card.CardService;
import com.example.flashcards.group.Group;
import com.example.flashcards.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlashcardsConfiguration {
    private final GroupService groupService;
    private final CardService cardService;

    @Autowired
    public FlashcardsConfiguration(GroupService groupService, CardService cardService) {
        this.groupService = groupService;
        this.cardService = cardService;
    }

    @Bean
    public CommandLineRunner initDb() {
        return args -> {
            Group group = groupService.createGroup(new Group("Basic Set"));
            cardService.createCard(new Card("Test 1", "Foo", group));
            cardService.createCard(new Card("Test 2", "Bar", group));
        };
    }
}
