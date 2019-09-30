package hu.virgo.pokerkez.hand;

import java.util.*;

public class PokerHandDescriptor {

    /*
    * allocation: - A kézben lévő 5 kártya eloszlását tárolja (azonos kártyák a kézben)
    *
    *             - Magas/szín/,sor:    0^2         = 0
    *             - Pár:                2^2         = 4
    *             - Két pár:            2^2 + 2^2   = 8
    *             - Drill:              3^2         = 9
    *             - Full:               3^2 + 2^2   = 13
    *             - Póker:              4^2         = 16
    *
    * isStraight: - Igaz: Ha egymást követik a lapok , azaz "sorunk van"
    *             - Hamis: egyébként
    *
    * isFlush: - Igaz: Ha minden szín azonos, azaz "színünk van"
    *          - Hamis: Egyébként
    *
    *
    * A fenti 3 információból egyértelműen meghatározható a pókerkéz értéke.
    *
    * */

    private int allocation;
    private boolean isStraight;
    private boolean isFlush;

    public PokerHandDescriptor(int allocation, boolean isStraight, boolean isFlush) {
        this.allocation = allocation;
        this.isStraight = isStraight;
        this.isFlush = isFlush;
    }

    public void addAllocation(int count) {
        this.allocation += Math.pow(count, 2);
    }

    public int getAllocation() {
        return allocation;
    }

    public void setAllocation(int allocation) {
        this.allocation = allocation;
    }

    public boolean isStraight() {
        return isStraight;
    }

    public void setStraight(boolean straight) {
        isStraight = straight;
    }

    public boolean isFlush() {
        return isFlush;
    }

    public void setFlush(boolean flush) {
        isFlush = flush;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHandDescriptor that = (PokerHandDescriptor) o;
        return allocation == that.allocation &&
                isStraight == that.isStraight &&
                isFlush == that.isFlush;
    }

    @Override
    public int hashCode() {
        return Objects.hash(allocation, isStraight, isFlush);
    }

    @Override
    public String toString() {
        return "PokerHandRank{" +
                "allocation=" + allocation +
                ", isStraight=" + isStraight +
                ", isFlush=" + isFlush +
                '}';
    }

    public enum Rank {
        HighCard,
        OnePair,
        TwoPair,
        ThreeOfAKind,
        Straight,
        Flush,
        FullHouse,
        FourOfAKind,
        StraightFlush,
        RoyalFlush
    }

}
