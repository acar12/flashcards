package com.example.flashcards.group;

import com.example.flashcards.card.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "card_group")
public class Group {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(
            mappedBy = "group",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Card> cards;
    @NonNull
    private String title;

    public Group(String title) {
        this.title = title;
    }

    public Group() {
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", cards=" + cards +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(cards, group.cards) && Objects.equals(title, group.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cards, title);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Long> getCardIds() {
        return cards.stream()
                .map(Card::getId)
                .collect(Collectors.toList());
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
