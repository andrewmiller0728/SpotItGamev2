package com.spotit.gamev2;

public class GameMaster {

    private Deck deck;
    private Player player;
    private Card[] currCardPair;

    public GameMaster(SymbolSet symbolSet, int symbolsPerCard, String playerName) {
        deck = new Deck(symbolSet, symbolsPerCard);
        player = new Player(playerName);
    }

    public Card[] updateCardPair() {
        currCardPair = new Card[2];
        currCardPair[0] = deck.pickCard();
        currCardPair[1] = deck.pickCard();
        if (currCardPair[0] == null || currCardPair[1] == null) {
            currCardPair = null;
        }
        return currCardPair;
    }

    public Card[] getCurrCardPair() {
        return currCardPair;
    }

    public boolean makeGuess(Symbol symbol) {
        // TODO: Implement makeGuess()
        return false;
    }

    public int getScore() {
        return player.getScore();
    }

}
