package com.spotit.gamev2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class SymbolSet {


    /* Variables */

    private int index;
    private String setName;
    private String[] names;
    private Color[] colors;
    private boolean[] used;


    /* Constructors */

    public SymbolSet(String setName, String[] names, Color[] colors) {
        if (names.length != colors.length) {
            throw new IllegalArgumentException("Array sizes do not match");
        }

        this.setName = setName;
        this.names = Arrays.copyOf(names, names.length);
        this.colors = Arrays.copyOf(colors, colors.length);
        this.used = new boolean[names.length];
        index = 0;
    }

    public SymbolSet(String setName, String[] names, Color[] colors, int currIndex) {
        if (names.length != colors.length) {
            throw new IllegalArgumentException("Array sizes do not match");
        }

        this.setName = setName;
        this.names = Arrays.copyOf(names, names.length);
        this.colors = Arrays.copyOf(colors, colors.length);
        this.used = new boolean[names.length];
        index = currIndex;
    }


    /* Methods */

    public Card[] generateCardsForDeck(int symbolPerCard) {
        if (!isPrime(symbolPerCard - 1)) {
            throw new IllegalArgumentException("Number of symbols must be prime + 1");
        }
        if (symbolPerCard + Math.pow(symbolPerCard - 1, 2) >= names.length) {
            throw new IllegalArgumentException("Not enough symbols available");
        }

        ArrayList<Card> deck = new ArrayList<>();

        for (int i = 0; i <= symbolPerCard - 1; i++) {
            ArrayList<Symbol> cardSymbols = new ArrayList<>();
            cardSymbols.add(new Symbol(names[0], colors[0]));
            for (int j = 1; j <= symbolPerCard - 1; j++) {
                int curr = (symbolPerCard - 1) + (symbolPerCard - 1) * (i - 1) + (j + 1);
                cardSymbols.add(new Symbol(names[curr], colors[curr]));
            }
            deck.add(new Card(shuffle(cardSymbols.toArray(new Symbol[0]))));
        }
        for (int i = 1; i <= symbolPerCard - 1; i++) {
            for (int j = 1; j <= symbolPerCard - 1; j++) {
                ArrayList<Symbol> cardSymbols = new ArrayList<>();
                cardSymbols.add(new Symbol(names[i + 1], colors[i + 1]));
                for (int k = 1; k <= symbolPerCard - 1; k++) {
                    int curr = (symbolPerCard + 1) + (symbolPerCard - 1) * (k - 1)
                            + ((i - 1) * (k - 1) + (j - 1)) % (symbolPerCard - 1);
                    cardSymbols.add(new Symbol(names[curr], colors[curr]));
                }
                deck.add(new Card(shuffle(cardSymbols.toArray(new Symbol[0]))));
            }
        }

        return deck.toArray(new Card[0]);
    }

    public String getSetName() {
        return setName;
    }

    public Symbol getNextSymbol() {
        if (index == names.length) {
            index = 0;
        }
        Symbol s = new Symbol(names[index], colors[index]);
        index++;
        return s;
    }

    public SymbolSet copy() {
        return new SymbolSet(this.setName, this.names, this.colors, this.index);
    }

    private boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    private Symbol[] shuffle(Symbol[] symbols) {
        for (int i = 0; i < symbols.length; i++) {
            int indexToSwap = MathUtils.random(symbols.length - 1);
            Symbol temp = symbols[indexToSwap];
            symbols[indexToSwap] = symbols[i];
            symbols[i] = temp;
        }
        return symbols;
    }


}
