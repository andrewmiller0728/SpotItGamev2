package com.spotit.gamev2;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {

    /* Variables */

    private TextureAtlas atlas;
    private String setName;
    private Card[] cards;
    private int index; // top of deck. ex: 5 cards in deck, index = 4

    /* Constructor */

    public Deck(String atlasFile, int symbolPerCard) {
        atlas = new TextureAtlas(atlasFile);
        setName = atlasFile.substring(0, atlasFile.indexOf("."));
        cards = generateCards(symbolPerCard);
        //cards = shuffleCards(cards); // TODO: Shuffle cards
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

    public TextureAtlas getTextureAtlas() {
        return atlas;
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

    private Card[] generateCards(int symbolPerCard) {
        if (!isPrime(symbolPerCard - 1)) {
            throw new IllegalArgumentException("Number of symbols must be prime + 1");
        }
        if (symbolPerCard + Math.pow(symbolPerCard - 1, 2) >= atlas.getRegions().size) {
            throw new IllegalArgumentException("Not enough symbols available");
        }

        ArrayList<Card> deck = new ArrayList<>();

        for (int i = 0; i <= symbolPerCard - 1; i++) {
            ArrayList<Symbol> cardSymbols = new ArrayList<>();
            cardSymbols.add(new Symbol("(0)", atlas.createSprite("(0)")));
            for (int j = 1; j <= symbolPerCard - 1; j++) {
                int curr = (symbolPerCard - 1) + (symbolPerCard - 1) * (i - 1) + (j + 1);
                cardSymbols.add(new Symbol("(" + curr + ")", atlas.createSprite("(" + curr + ")")));
            }
            deck.add(new Card(shuffleSymbols(cardSymbols.toArray(new Symbol[0]))));
        }
        for (int i = 1; i <= symbolPerCard - 1; i++) {
            for (int j = 1; j <= symbolPerCard - 1; j++) {
                ArrayList<Symbol> cardSymbols = new ArrayList<>();
                cardSymbols.add(new Symbol("(" + (i + 1) + ")", atlas.createSprite("(" + (i + 1) + ")")));
                for (int k = 1; k <= symbolPerCard - 1; k++) {
                    int curr = (symbolPerCard + 1) + (symbolPerCard - 1) * (k - 1)
                            + ((i - 1) * (k - 1) + (j - 1)) % (symbolPerCard - 1);
                    cardSymbols.add(new Symbol("(" + curr + ")", atlas.createSprite("(" + curr + ")")));
                }
                deck.add(new Card(shuffleSymbols(cardSymbols.toArray(new Symbol[0]))));
            }
        }

        return deck.toArray(new Card[0]);
    }

    private boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    private Symbol[] shuffleSymbols(Symbol[] symbols) {
        for (int i = 0; i < symbols.length; i++) {
            int indexToSwap = MathUtils.random(symbols.length - 1);
            Symbol temp = symbols[indexToSwap];
            symbols[indexToSwap] = symbols[i];
            symbols[i] = temp;
        }
        return symbols;
    }

    private Card[] shuffleCards(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            int indexToSwap = MathUtils.random(cards.length - 1);
            Card temp = cards[indexToSwap];
            cards[indexToSwap] = cards[i];
            cards[i] = temp;
        }
        return cards;
    }

}
