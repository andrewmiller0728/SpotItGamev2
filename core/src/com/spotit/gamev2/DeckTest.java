package com.spotit.gamev2;

import com.badlogic.gdx.graphics.Color;

public class DeckTest {

    public static void main(String[] args) {

        String setName = "mySet";
        String[] names = {
                "Triangle",
                "Square",
                "Circle",
                "Rectangle"
        };
        Color[] colors = {
                Color.RED,
                Color.BLUE,
                Color.GREEN,
                Color.BROWN
        };
        SymbolSet mySymbolSet = new SymbolSet(setName, names, colors);

//        Deck myDeck = new Deck(mySymbolSet, 5,2);

//        System.out.println(myDeck.toFormattedString());

    }

}
