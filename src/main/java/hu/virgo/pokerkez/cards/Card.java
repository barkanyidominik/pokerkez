package hu.virgo.pokerkez.cards;

public class Card implements Comparable<Card>{

    private Suits suit;
    private Ranks rank;

    public Card(Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public int getRankValue() {
        return rank.ordinal();
    }

    public boolean equalsRank(Card card) { return rank == card.rank; }

    public boolean equalsRank(Ranks rank) {
        return this.rank == rank;
    }

    public boolean equalsSuit(Card card) {
        return suit == card.suit;
    }

    public boolean equalsSuit(Suits suit) { return this.suit == suit; }

    public boolean isGreaterThan(Card card) { return this.compareTo(card) > 0; }

    public boolean isLessThan(Card card) { return this.compareTo(card) < 0; }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(rank.ordinal(), o.rank.ordinal());
    }

    @Override
    public String toString() {
        return rank + " - " + suit;
    }

    public enum Suits {
        Hearts,
        Diamonds,
        Spades,
        Clubs
    };

    public enum Ranks {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace
    };
}
