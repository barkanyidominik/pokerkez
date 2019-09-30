package hu.virgo.pokerkez.hand;

import hu.virgo.pokerkez.cards.Card;

import java.util.Objects;

public class PokerHandRank {
    private PokerHandDescriptor.Rank rank;
    private Card.Ranks high;

    public PokerHandRank(PokerHandDescriptor.Rank rank, Card.Ranks high) {
        this.rank = rank;
        this.high = high;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHandRank that = (PokerHandRank) o;
        return rank == that.rank &&
                high == that.high;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, high);
    }

    @Override
    public String toString() {
        return rank + ", " + high;
    }
}
