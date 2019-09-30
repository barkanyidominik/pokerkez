package hu.virgo.pokerkez;

import java.util.ArrayList;
import java.util.List;

import hu.virgo.pokerkez.cards.Card;
import hu.virgo.pokerkez.cards.Card.Ranks;
import hu.virgo.pokerkez.cards.Card.Suits;
import hu.virgo.pokerkez.cards.Deck;
import hu.virgo.pokerkez.hand.PokerHand;

public class App {

    private static Deck deck;

    public static void main(String... args) {
        int numberOfDeck = parameterCheck(args);
        System.out.println("Number of decks: " + numberOfDeck);
        generateShuffledDeck(numberOfDeck);
        List<Card> cards = deck.getTopFive();
        PokerHand pokerHand = new PokerHand(cards);
        System.out.println(pokerHand.getCards());
        System.out.println(pokerHand.getRank());
    }

    private static int parameterCheck(String... args) {
        int numberOfDeck = 1;

        try {
            numberOfDeck = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Parameter must be a number. The default parameter is 1.\n");
        }

        return numberOfDeck;
    }

    private static void generateShuffledDeck(int numberOfDeck) {
        List<Card> cards = new ArrayList<Card>();
        deck = new Deck(cards);

        for (int i = 0; i < numberOfDeck; i++)
        {
            for (Ranks rank : Ranks.values()){
                for (Suits suit : Suits.values()) {
                    deck.add(new Card(suit, rank));
                }
            }
        }

        deck.shuffle();
    }

}
