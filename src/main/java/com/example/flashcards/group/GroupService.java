package com.example.flashcards.group;

import com.example.flashcards.card.Card;
import com.example.flashcards.card.CardRepository;
import com.example.flashcards.card.CardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final CardService cardService;

    @Autowired
    public GroupService(GroupRepository groupRepository, CardService cardService) {
        this.groupRepository = groupRepository;
        this.cardService = cardService;
    }

    public Group createGroup(Group group) {
        if (group.getTitle().isBlank())
            throw new IllegalArgumentException("cannot set group title to blank string");
        return groupRepository.save(group);
    }

    public Card createCardInGroup(Long id, Card card) {
        return cardService.createCard(new Card(card.getFront(), card.getBack(), getById(id)));
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("group id of " + id + " doesn't exist"));
    }

    public Group updateGroup(Long id, String title) {
        Group group = getById(id);
        group.setTitle(title);
        return group;
    }

    public void deleteGroup(Long id) {
        Group group = getById(id);
        groupRepository.delete(group);
    }
}
