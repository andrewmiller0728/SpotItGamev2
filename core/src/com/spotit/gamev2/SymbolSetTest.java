package com.spotit.gamev2;

import com.badlogic.gdx.graphics.Color;

public class SymbolSetTest {

    public static void main(String[] args) {

        String setName = "mySet";
        String[] names = {
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T"
        };
        Color[] colors = {
                Color.RED,
                Color.BLUE,
                Color.GREEN,
                Color.BLACK,
                Color.BROWN,
                Color.CHARTREUSE,
                Color.CORAL,
                Color.CYAN,
                Color.FIREBRICK,
                Color.ROYAL,
                Color.RED,
                Color.BLUE,
                Color.GREEN,
                Color.BLACK,
                Color.BROWN,
                Color.CHARTREUSE,
                Color.CORAL,
                Color.CYAN,
                Color.FIREBRICK,
                Color.ROYAL
        };

        SymbolSet mySymbolSet = new SymbolSet(setName, names, colors);
        Card[] deck = mySymbolSet.generateCardsForDeck(4);

        for (Card card : deck) {
            System.out.println(card.toFormattedString());
        }

    }

}
