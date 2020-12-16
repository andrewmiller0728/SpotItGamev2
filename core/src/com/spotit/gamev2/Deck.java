package com.spotit.gamev2;

import java.util.Arrays;

public class Deck {

    /* Variables */

    private String setName;
    private Card[] cards;
    private int index; // top of deck. ex: 5 cards in deck, index = 4


    /* Constructor */

    public Deck(SymbolSet set, int symbolPerCard) {
        setName = set.getSetName();
        cards = set.generateCardsForDeck(symbolPerCard);
        index = cards.length - 1;
    }


    /* Methods */

    public Card pickCard() {
        if (index == -1) {
            return null;
        }
        else {
            Card c = cards[index];
            cards[index] = null;
            index--;
            return c;
        }
    }

    private boolean contains(Card c) {
        for (Card d : cards) {
            if (d.equals(c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Deck->{" +
                "setName=\"" + setName +
                "\", index=" + index +
                ", cards=" + Arrays.toString(cards) +
                "}";
    }

    public String toFormattedString() {
        String s = "Deck->{" +
                "\n\tsetName=\"" + setName +
                "\",\n\tindex=" + index +
                ",\n\tcards=\n";
        for (int i = 0; i < cards.length; i++) {
            s = s.concat(String.format("\t\t%s\n", cards[i].toFormattedString()));
        }
        return s.concat("}");
    }


}
