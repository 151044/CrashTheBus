package com.colin.games.bus.cards;

public interface Suit {
    String getName();
    enum DefaultSuits implements Suit{
        HEARTS,SPADES,CLUBS,DIAMONDS;

        @Override
        public String getName() {
            return toString();
        }

        @Override
        public String toString() {
            String low = name().toLowerCase();
            return Character.toUpperCase(low.charAt(0)) + low.substring(1);
        }
    }
}
