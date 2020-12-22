package com.spotit.gamev2;

public class GameMaster {

    private final Deck deck;
    private final Player player;

    private Card[] currCardPair;
    private SymbolSprite[][] currSymbolSprites;

    public GameMaster(SymbolSet symbolSet, int symbolsPerCard, String playerName) {
        deck = new Deck(symbolSet, symbolsPerCard);
        player = new Player(playerName);
        currCardPair = null;
        currSymbolSprites = null;
    }

    public void updateCardPair() {
        currCardPair = new Card[2];
        currCardPair[0] = deck.pickCard();
        currCardPair[1] = deck.pickCard();
        if (currCardPair[0] == null || currCardPair[1] == null) {
            currCardPair = null;
        }
    }

    public Card[] getCurrCardPair() {
        return currCardPair;
    }

    public void setCurrSymbolSprites(SymbolSprite[][] currSymbolSprites) {
        this.currSymbolSprites = currSymbolSprites;
    }

    public SymbolSprite[][] getCurrSymbolSprites() {
        return currSymbolSprites;
    }

    public boolean makeGuess(Symbol symbol) {
        for (int i = 0; i < currSymbolSprites[0].length; i++) {
            for (int j = 0; j < currSymbolSprites[1].length; j++) {
                if (currSymbolSprites[0][i].getSymbol().equals(currSymbolSprites[1][j].getSymbol())) {
                    if (currSymbolSprites[0][i].getSymbol().equals(symbol)) {
                        player.addPoint();
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }
}
