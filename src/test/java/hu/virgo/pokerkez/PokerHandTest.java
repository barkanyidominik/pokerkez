package hu.virgo.pokerkez;

import hu.virgo.pokerkez.cards.Card;
import hu.virgo.pokerkez.hand.PokerHand;
import hu.virgo.pokerkez.hand.PokerHandRank;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import hu.virgo.pokerkez.cards.Card.Ranks;
import hu.virgo.pokerkez.cards.Card.Suits;

import static hu.virgo.pokerkez.hand.PokerHandDescriptor.Rank.*;

import static org.junit.Assert.*;


public class PokerHandTest {

    PokerHand pokerHand;

    @Before
    public void init() {
        pokerHand = new PokerHand();
    }

    @Test
    public void highCardTest() {
        List<Card> cards = new ArrayList<Card>();

        //High Card
        cards.add(new Card(Suits.Diamonds, Ranks.Three));
        cards.add(new Card(Suits.Clubs, Ranks.Jack));
        cards.add(new Card(Suits.Spades, Ranks.Eight));
        cards.add(new Card(Suits.Hearts, Ranks.Four));
        cards.add(new Card(Suits.Spades, Ranks.Two));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(HighCard, Ranks.Jack));
    }

    @Test
    public void onePairTest() {
        List<Card> cards = new ArrayList<Card>();

        //One Pair
        cards.add(new Card(Suits.Hearts, Ranks.Ace));
        cards.add(new Card(Suits.Diamonds, Ranks.Ace));
        cards.add(new Card(Suits.Clubs, Ranks.Eight));
        cards.add(new Card(Suits.Spades, Ranks.Four));
        cards.add(new Card(Suits.Hearts, Ranks.Seven));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(OnePair, Ranks.Ace));
    }

    @Test
    public void twoPairTest() {
        List<Card> cards = new ArrayList<Card>();

        //Two Pair
        cards.add(new Card(Suits.Clubs, Ranks.Four));
        cards.add(new Card(Suits.Spades, Ranks.Four));
        cards.add(new Card(Suits.Clubs, Ranks.Three));
        cards.add(new Card(Suits.Diamonds, Ranks.Three));
        cards.add(new Card(Suits.Clubs, Ranks.Queen));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(TwoPair, Ranks.Four));
    }

    @Test
    public void threeOfAKindTest() {
        List<Card> cards = new ArrayList<Card>();

        // Three of a kind
        cards.add(new Card(Suits.Clubs, Ranks.Seven));
        cards.add(new Card(Suits.Diamonds, Ranks.Seven));
        cards.add(new Card(Suits.Spades, Ranks.Seven));
        cards.add(new Card(Suits.Clubs, Ranks.King));
        cards.add(new Card(Suits.Diamonds, Ranks.Three));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(ThreeOfAKind, Ranks.Seven));
    }

    @Test
    public void straightTest() {
        List<Card> cards = new ArrayList<Card>();

        // Straight
        cards.add(new Card(Suits.Clubs, Ranks.Nine));
        cards.add(new Card(Suits.Diamonds, Ranks.Eight));
        cards.add(new Card(Suits.Spades, Ranks.Seven));
        cards.add(new Card(Suits.Diamonds, Ranks.Six));
        cards.add(new Card(Suits.Hearts, Ranks.Five));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(Straight, Ranks.Nine));
    }

    @Test
    public void flushTest() {
        List<Card> cards = new ArrayList<Card>();

        // Flush
        cards.add(new Card(Suits.Spades, Ranks.Four));
        cards.add(new Card(Suits.Spades, Ranks.Jack));
        cards.add(new Card(Suits.Spades, Ranks.Eight));
        cards.add(new Card(Suits.Spades, Ranks.Two));
        cards.add(new Card(Suits.Spades, Ranks.Nine));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(Flush, Ranks.Jack));
    }

    @Test
    public void fullHouseTest() {
        List<Card> cards = new ArrayList<Card>();

        // Full House
        cards.add(new Card(Suits.Hearts, Ranks.Ten));
        cards.add(new Card(Suits.Diamonds, Ranks.Ten));
        cards.add(new Card(Suits.Spades, Ranks.Ten));
        cards.add(new Card(Suits.Clubs, Ranks.Nine));
        cards.add(new Card(Suits.Diamonds, Ranks.Nine));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(FullHouse, Ranks.Ten));
    }

    @Test
    public void fullHouseTest2() {
        List<Card> cards = new ArrayList<Card>();

        // Full House
        cards.add(new Card(Suits.Hearts, Ranks.Ten));
        cards.add(new Card(Suits.Diamonds, Ranks.Ten));
        cards.add(new Card(Suits.Spades, Ranks.Nine));
        cards.add(new Card(Suits.Clubs, Ranks.Nine));
        cards.add(new Card(Suits.Diamonds, Ranks.Nine));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(FullHouse, Ranks.Nine));
    }

    @Test
    public void fourOfAKindTest() {
        List<Card> cards = new ArrayList<Card>();

        // Four of a kind
        cards.add(new Card(Suits.Hearts, Ranks.Jack));
        cards.add(new Card(Suits.Diamonds, Ranks.Jack));
        cards.add(new Card(Suits.Spades, Ranks.Jack));
        cards.add(new Card(Suits.Clubs, Ranks.Jack));
        cards.add(new Card(Suits.Diamonds, Ranks.Seven));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(FourOfAKind, Ranks.Jack));
    }

    @Test
    public void straightFlushTest() {
        List<Card> cards = new ArrayList<Card>();

        // Straight flush
        cards.add(new Card(Suits.Clubs, Ranks.Eight));
        cards.add(new Card(Suits.Clubs, Ranks.Seven));
        cards.add(new Card(Suits.Clubs, Ranks.Six));
        cards.add(new Card(Suits.Clubs, Ranks.Five));
        cards.add(new Card(Suits.Clubs, Ranks.Four));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(StraightFlush, Ranks.Eight));
    }

    @Test
    public void royalFlushTest() {
        List<Card> cards = new ArrayList<Card>();

        // Royal flush
        cards.add(new Card(Suits.Diamonds, Ranks.Ace));
        cards.add(new Card(Suits.Diamonds, Ranks.King));
        cards.add(new Card(Suits.Diamonds, Ranks.Queen));
        cards.add(new Card(Suits.Diamonds, Ranks.Jack));
        cards.add(new Card(Suits.Diamonds, Ranks.Ten));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(RoyalFlush, Ranks.Ace));
    }

    @Test
    public void straightStartWithAceTest() {
        List<Card> cards = new ArrayList<Card>();

        // Straight
        cards.add(new Card(Suits.Clubs, Ranks.Ace));
        cards.add(new Card(Suits.Diamonds, Ranks.Two));
        cards.add(new Card(Suits.Spades, Ranks.Three));
        cards.add(new Card(Suits.Diamonds, Ranks.Four));
        cards.add(new Card(Suits.Hearts, Ranks.Five));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(Straight, Ranks.Five));
    }

    @Test
    public void straightFlushStartWithAceTest() {
        List<Card> cards = new ArrayList<Card>();

        // Straight
        cards.add(new Card(Suits.Hearts, Ranks.Ace));
        cards.add(new Card(Suits.Hearts, Ranks.Two));
        cards.add(new Card(Suits.Hearts, Ranks.Three));
        cards.add(new Card(Suits.Hearts, Ranks.Four));
        cards.add(new Card(Suits.Hearts, Ranks.Five));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        System.out.println(result);
        assertEquals(result, new PokerHandRank(StraightFlush, Ranks.Five));
    }

    @Test
    public void multiDeckFOAKTest() {
        List<Card> cards = new ArrayList<Card>();

        // Four of a kind
        cards.add(new Card(Suits.Hearts, Ranks.Jack));
        cards.add(new Card(Suits.Diamonds, Ranks.Jack));
        cards.add(new Card(Suits.Diamonds, Ranks.Jack)); // Több pakli miatt többször szerepelhet azonos lap
        cards.add(new Card(Suits.Spades, Ranks.Jack));
        cards.add(new Card(Suits.Clubs, Ranks.Jack));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(FourOfAKind, Ranks.Jack));
    }

    @Test
    public void multiDeckFOAKTest2() {
        List<Card> cards = new ArrayList<Card>();

        // Öt paklis eset
        cards.add(new Card(Suits.Hearts, Ranks.Jack));
        cards.add(new Card(Suits.Hearts, Ranks.Jack));
        cards.add(new Card(Suits.Hearts, Ranks.Jack));
        cards.add(new Card(Suits.Hearts, Ranks.Jack));
        cards.add(new Card(Suits.Hearts, Ranks.Jack));

        pokerHand.setCards(cards);
        PokerHandRank result = pokerHand.getRank();
        assertEquals(result, new PokerHandRank(FourOfAKind, Ranks.Jack));
    }
}
