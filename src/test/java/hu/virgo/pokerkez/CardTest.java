package hu.virgo.pokerkez;

import hu.virgo.pokerkez.cards.Card;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    private Card card;
    private Card card2;
    private Card card3;
    private Card card4;

    @Before
    public void init() {
        card = new Card(Card.Suits.Clubs, Card.Ranks.Ace);
        card2 = new Card(Card.Suits.Hearts, Card.Ranks.Ace);
        card3 = new Card(Card.Suits.Clubs, Card.Ranks.Two);
        card4 = new Card(Card.Suits.Diamonds, Card.Ranks.King);
    }

    @Test
    public void getRankValueTest() {
        assertEquals(card.getRankValue(), 12);
        assertEquals(card2.getRankValue(), 12);
        assertEquals(card3.getRankValue(), 0);
        assertEquals(card4.getRankValue(), 11);
    }

    @Test
    public void equalsRankTest() {
        assertTrue(card.equalsRank(card2));
        assertFalse(card.equalsRank(card4));
    }

    @Test
    public void equalsSuitTest() {
        assertTrue(card.equalsSuit(card3));
        assertFalse(card.equalsSuit(card2));
    }

    @Test
    public void isGreaterThan() {
        assertTrue(card.isGreaterThan(card3));
        assertFalse(card.isGreaterThan(card2));
        assertFalse(card4.isGreaterThan(card));
    }

    @Test
    public void isLessThan() {
        assertTrue(card4.isLessThan(card));
        assertFalse(card.isLessThan(card4));
        assertFalse(card.isGreaterThan(card2));
    }

    @Test
    public void compareToTes() {
        assertEquals(card.compareTo(card2), 0);
        assertEquals(card.compareTo(card3), 1);
        assertEquals(card3.compareTo(card4), -1);
    }

}
