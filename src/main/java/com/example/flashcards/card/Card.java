package com.example.flashcards.card;

import com.example.flashcards.group.Group;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String front;
    @NonNull
    private String back;
    @ManyToOne
    private Group group;

    public Card() {
    }

    public Card(String front, String back) {
        this.front = front;
        this.back = back;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", front='" + front + '\'' +
                ", back='" + back + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && Objects.equals(front, card.front) && Objects.equals(back, card.back);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, front, back);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
