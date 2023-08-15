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

    @Autowired
    public FlashcardsConfiguration(GroupService groupService) {
        this.groupService = groupService;
    }

    @Bean
    public CommandLineRunner initDb() {
        return args -> {
            Long id = groupService.createGroup(new Group("Basic Set")).getId();
            groupService.createCardInGroup(id, new Card("Test 1", "Foo"));
            groupService.createCardInGroup(id, new Card("Test 2", "Bar"));
        };
    }
}
