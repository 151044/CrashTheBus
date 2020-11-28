package com.colin.games.bus.cards;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private Suit suit;
    private int order;
    private static Map<Integer,String> nameMap = new HashMap<>();
    static{
        nameMap.put(1,"Ace");
        nameMap.put(11,"Jack");
        nameMap.put(12,"Queen");
        nameMap.put(13,"King");
    }
    public Card(Suit suit, int order){
        if(order < 1){
            throw new IllegalStateException("Order less than one?");
        }
        this.suit = suit;
        this.order = order;
    }

    @Override
    public String toString() {
        return nameOf(order) + " of " + suit.getName();
    }
    private static String nameOf(int i){
        if(i < 1){
            throw new IllegalStateException("Order less than one?");
        }
        //Lookup in map first, to allow for overrides
        if(nameMap.containsKey(i)){
            return nameMap.get(i);
        }
        return String.valueOf(i);
    }
}
