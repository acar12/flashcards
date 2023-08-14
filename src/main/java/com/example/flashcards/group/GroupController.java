package com.example.flashcards.group;

import com.example.flashcards.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public Group createGroup(Group group) {
        return groupService.createGroup(group);
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("{id}")
    public Group getGroupById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @PutMapping("{id}")
    public Group updateGroup(@PathVariable Long id, String title) {
        return groupService.updateGroup(id, title);
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping("{id}/cards")
    public List<Card> getAllCardsByGroupId(@PathVariable Long id) {
        return groupService.getAllCardsFromGroupId(id);
    }

    @PostMapping("{id}/cards")
    public Card createCardInGroup(@PathVariable Long id, Card card) {
        return groupService.createCardInGroup(id, card);
    }
}
