package com.spotit.gamev2;

public class GameMaster {

    private final int GUESS_TRIES = 3;

    private Deck deck;
    private Player player;
    private Card[] currCardPair;
    private SymbolSprite[][] currSymbolSprites;
    private boolean recentAnswer;

    public GameMaster(SymbolSet symbolSet, int symbolsPerCard, String playerName) {
        deck = new Deck(symbolSet, symbolsPerCard);
        player = new Player(playerName);
        currCardPair = null;
        currSymbolSprites = null;
        recentAnswer = false;
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

    public void setCurrSymbolSprites(SymbolSprite[][] currSymbolSprites) {
        this.currSymbolSprites = currSymbolSprites;
    }

    public SymbolSprite[][] getCurrSymbolSprites() {
        return currSymbolSprites;
    }

    public boolean makeGuess(Symbol symbol) {
        System.out.printf("\tGuessed symbol = %s\n", symbol.getName());
        for (int i = 0; i < currSymbolSprites[0].length; i++) {
            for (int j = i; j < currSymbolSprites[1].length; j++) {
                if (currSymbolSprites[0][i].getSymbol().equals(currSymbolSprites[1][j].getSymbol())) {
                    if (currSymbolSprites[0][i].getSymbol().equals(symbol)) {
                        recentAnswer = true;
                        player.addPoint();
                        return true;
                    }
                    else {
                        recentAnswer = false;
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean getRecentAnswer() {
        return recentAnswer;
    }

    public Player getPlayer() {
        return player;
    }
}
