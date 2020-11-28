package com.colin.test;

import com.colin.games.bus.cards.Card;
import com.colin.games.bus.cards.Suit;

public class Main {
    public static void main(String[] args) {
        for(int i = 1; i < 14; i++){
            for(Suit s : Suit.DefaultSuits.values()){
                System.out.println(new Card(s,i));
            }
        }
    }
}
