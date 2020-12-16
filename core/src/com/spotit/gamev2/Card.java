package com.spotit.gamev2;


import java.util.Arrays;

public class Card {


    /* Variables */

    private Symbol[] symbols;


    /* Constructor */

    public Card(Symbol[] symbols) {
        this.symbols = symbols;
    }


    /* Methods */

    public boolean contains(Symbol s) {
        for (Symbol t : symbols) {
            if (t.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public Symbol[] getSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        return "Card->{" +
                "symbols=" + Arrays.toString(symbols) +
                "}";
    }

    public String toFormattedString() {
        String s = "Card->{\n\t\tsymbols=\n";
        for (int i = 0; i < symbols.length; i++) {
            s = s.concat(String.format("\t\t\t%s\n", symbols[i].toString()));
        }
        return s.concat("\t\t}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Card card = (Card) o;
        if (!Arrays.equals(this.symbols, card.symbols)) {
            for (Symbol s : this.symbols) {
                if (!card.contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(symbols);
    }


}
