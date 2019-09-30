package hu.virgo.pokerkez.cards;

import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int size() {
        return cards.size();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public List<Card> getTopFive(){
        return cards.subList(0, 5);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
