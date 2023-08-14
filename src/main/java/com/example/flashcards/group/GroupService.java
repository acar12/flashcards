package com.example.flashcards.group;

import com.example.flashcards.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group group) {
        if (group.getTitle().isBlank())
            throw new IllegalArgumentException("cannot set group title to blank string");
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

    public void deleteCard(Long id) {
        Group group = getById(id);
        groupRepository.delete(group);
    }
}
