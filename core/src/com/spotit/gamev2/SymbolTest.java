package com.spotit.gamev2;

import com.badlogic.gdx.graphics.Color;

public class SymbolTest {

    public static void main(String[] args) {

        Symbol[] symbols = {
                new Symbol("Triangle", Color.BLUE),
                new Symbol("Triangle", Color.BLUE),
                new Symbol("Circle", Color.GOLD),
                new Symbol("Square", Color.ORANGE),
                new Symbol("Square", Color.PURPLE)
        };

        for (Symbol mySymbol : symbols) {
            System.out.println(mySymbol.toString());
        }
        System.out.println();

        if (symbols[0].equals(symbols[1])) {
            System.out.println("Triangles match");
        }

        if (!symbols[3].equals(symbols[4])) {
            System.out.println("Squares do not match");
        }

    }

}
