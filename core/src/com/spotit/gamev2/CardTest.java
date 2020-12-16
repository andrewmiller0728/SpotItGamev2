package com.spotit.gamev2;

import com.badlogic.gdx.graphics.Color;

public class CardTest {

    public static void main(String[] args) {

        String setName = "mySet";
        String[] names = {
                "Triangle",
                "Square",
                "Circle"
        };
        Color[] colors = {
                Color.RED,
                Color.BLUE,
                Color.GREEN
        };
        SymbolSet mySymbolSet = new SymbolSet(setName, names, colors);

//        int symbCount = 3;
//        Card myCard = new Card(symbCount, mySymbolSet.copy());
//        Card myCardCopy = new Card(symbCount, mySymbolSet.copy());
//
//        System.out.println(myCard.toString());
//
//        if (myCard.contains(new Symbol("Triangle", Color.RED))
//                && !myCard.contains(new Symbol("Rectangle", Color.PURPLE))) {
//            System.out.println("Contains() works");
//        }
//
//        if (myCard.equals(myCardCopy)) {
//            System.out.println("Equals() works");
//        }

    }

}
