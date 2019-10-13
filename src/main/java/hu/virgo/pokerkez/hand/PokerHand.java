package hu.virgo.pokerkez.hand;

import java.util.*;

import hu.virgo.pokerkez.cards.Card;
import hu.virgo.pokerkez.hand.PokerHandDescriptor.Rank;

public class PokerHand {

    private List<Card> cards;

    private PokerHandDescriptor descriptor;
    private int allocationCounter;
    private Card lastCard;
    private Card highCard;

    public PokerHand() {
        descriptor = new PokerHandDescriptor(0, true, true);
        allocationCounter = 1;
    }

    public PokerHand(List<Card> cards){
        this();
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public PokerHandRank getRank() {
        sort();

        for(Card card : cards) {
            if(lastCard != null){
                calculateAllocation(card);
                straightCheck(card);
                flushCheck(card);
            }
            lastCard = card;
        }
        handleFinalExceptions();

        Rank rank = findRank();
        Card.Ranks high = getHighByRank(rank);

        return new PokerHandRank(rank, high);
    }

    private Rank findRank() {
        Rank rank;
        if(isRoyalFlush()) {
            rank = Rank.RoyalFlush;
        } else {
            rank = handCases.get(descriptor);
        }
        return rank;
    }

    // Royal Flush kezelése
    private boolean isRoyalFlush() {
        Card firstCard = cards.get(0);

        return  descriptor.isStraight() &&
                descriptor.isFlush() &&
                firstCard.equalsRank(Card.Ranks.Ten);
    }

    // Kártyák rendezése
    private void sort() {
        Collections.sort(cards);
    }

    // Eloszlás számítás (pár, két pár, drill, ...)
    private void calculateAllocation(Card card) {
        if(lastCard.equalsRank(card)) {
            allocationCounter++;
        } else {
            if(allocationCounter > 1) {
                descriptor.addAllocation(allocationCounter);
                allocationCounter = 1;
                if(highCard == null || lastCard.isGreaterThan(highCard)) {
                    highCard = lastCard;
                }
            }
        }
    }

    // Sor detektálás
    private void straightCheck(Card card) {
        if(descriptor.isStraight()) {
            if(card.getRankValue() != lastCard.getRankValue() + 1){
                if(!lastCard.equalsRank(Card.Ranks.Five) || !card.equalsRank(Card.Ranks.Ace)) { // Arra az esetre ha az ACE a legkisebb lap szerepét töltené be a sorban.
                    descriptor.setStraight(false);
                }
            }
        }
    }

    // Szín detektálás
    private void flushCheck(Card card) {
        if(descriptor.isFlush() && !lastCard.equalsSuit(card)) {
            descriptor.setFlush(false);
        }
    }

    private void handleFinalExceptions() {
        // Ha a pokerkéz végén vannak azonos lapok, akkor a ciklusban nem mindent kezeltük le.
        if(allocationCounter > 1) {
            descriptor.addAllocation(allocationCounter);
            if(highCard == null || lastCard.isGreaterThan(highCard)) {
                highCard = lastCard;
            }
        }
    }

    /*
     * Pókerkéz értékek megszerzése
     * Pár, Két pár, Drill, Full, Póker esetén már a highCard változóban van a pókerkéz értéke.
     * Magas lap, Sor, Szín, Színsor, Royal Flush esetén az utolsó kártya kell, a rendezés miatt.
     * Full house esetén biztosan a középső elem kell, a rendezett kártyák miatt.
     */
    private Card.Ranks getHighByRank(Rank rank) {
        switch (rank) {
            case HighCard:
            case Straight:
            case Flush:
            case StraightFlush:
            case RoyalFlush:
                highCard = lastCard;
                break;
            case FullHouse:
                highCard = cards.get(2);
                break;
        }
        Card.Ranks high = highCard.getRank();
        // Öt magas sorok kezelése. Ha sor és az első kártya KETTES, akkor 5 magas sorunk van. (Az ACE a végén van, a növekvő sorrend miatt)
        Card firstCard = cards.get(0);
        if(descriptor.isStraight() && firstCard.equalsRank(Card.Ranks.Two) && lastCard.equalsRank(Card.Ranks.Ace)) {
            high = Card.Ranks.Five;
        }
        return high;
    }

    private final Map<PokerHandDescriptor, Rank> handCases = new HashMap<PokerHandDescriptor, Rank>() {{
        put(new PokerHandDescriptor(0, false, false), Rank.HighCard);
        put(new PokerHandDescriptor(4, false, false), Rank.OnePair);
        put(new PokerHandDescriptor(8, false, false), Rank.TwoPair);
        put(new PokerHandDescriptor(9, false, false), Rank.ThreeOfAKind);
        put(new PokerHandDescriptor(0, true, false), Rank.Straight);
        put(new PokerHandDescriptor(0, false, true), Rank.Flush);
        put(new PokerHandDescriptor(4, false, true), Rank.Flush);
        put(new PokerHandDescriptor(8, false, true), Rank.Flush);
        put(new PokerHandDescriptor(9, false, true), Rank.Flush);
        put(new PokerHandDescriptor(13, false, true), Rank.FullHouse);
        put(new PokerHandDescriptor(13, false, false), Rank.FullHouse);
        put(new PokerHandDescriptor(16, false, true), Rank.FourOfAKind);
        put(new PokerHandDescriptor(16, false, false), Rank.FourOfAKind);
        put(new PokerHandDescriptor(25, false, true), Rank.FourOfAKind);
        put(new PokerHandDescriptor(25, false, false), Rank.FourOfAKind);
        put(new PokerHandDescriptor(0, true, true), Rank.StraightFlush);
    }};

/*
    // Egy paklis esetre:

    private static Map<PokerHandDescriptor, Rank> handCasesForOneDeck = new HashMap<PokerHandDescriptor, Rank>() {{
        put(new PokerHandDescriptor(0, false, false), Rank.HighCard);
        put(new PokerHandDescriptor(4, false, false), Rank.OnePair);
        put(new PokerHandDescriptor(8, false, false), Rank.TwoPair);
        put(new PokerHandDescriptor(9, false, false), Rank.ThreeOfAKind);
        put(new PokerHandDescriptor(0, true, false), Rank.Straight);
        put(new PokerHandDescriptor(0, false, true), Rank.Flush);
        put(new PokerHandDescriptor(13, false, false), Rank.FullHouse);
        put(new PokerHandDescriptor(16, false, false), Rank.FourOfAKind);
        put(new PokerHandDescriptor(0, true, true), Rank.StraightFlush);
    }};
 */

}
