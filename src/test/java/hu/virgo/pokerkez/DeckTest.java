package hu.virgo.pokerkez;

import hu.virgo.pokerkez.cards.Card;
import hu.virgo.pokerkez.cards.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck deck;

    @Before
    public void init() {
        List<Card> cards = new ArrayList<>();
        for (Card.Ranks rank : Card.Ranks.values()){
            for (Card.Suits suit : Card.Suits.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        deck = new Deck(cards);
    }

    @Test
    public void addTest() {
        Card card = new Card(Card.Suits.Diamonds, Card.Ranks.Ace);
        deck.add(card);
        assertEquals(deck.getCards().get(52), card);
    }

    @Test
    public void sizeTest() {
        assertEquals(deck.size(), 52);

        deck.add(new Card(Card.Suits.Diamonds, Card.Ranks.Ace));
        deck.add(new Card(Card.Suits.Hearts, Card.Ranks.King));

        assertEquals(deck.size(), 54);
    }

    @Test
    public void shuffleTest() {
        List<Card> cards = new ArrayList<Card>(deck.getCards());
        assertEquals(cards, deck.getCards());
        deck.shuffle();
        assertNotEquals(cards, deck.getCards());
    }

    @Test
    public void getPokerHandTest() {
        Card hand0 = deck.getCards().get(0);
        Card hand1 = deck.getCards().get(1);
        Card hand2 = deck.getCards().get(2);
        Card hand3 = deck.getCards().get(3);
        Card hand4 = deck.getCards().get(4);

        List<Card> hands = deck.getTopFive();

        assertEquals(hands.size(), 5);
        assertEquals(hands.get(0), hand0);
        assertEquals(hands.get(1), hand1);
        assertEquals(hands.get(2), hand2);
        assertEquals(hands.get(3), hand3);
        assertEquals(hands.get(4), hand4);
    }


}
