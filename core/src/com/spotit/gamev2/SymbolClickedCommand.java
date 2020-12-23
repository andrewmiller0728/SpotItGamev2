package com.spotit.gamev2;

public class SymbolClickedCommand extends Command {

    private Symbol symbol;
    private CardPair cardPair;

    public SymbolClickedCommand(Symbol symbol, CardPair cardPair) {
        super();
        this.symbol = symbol;
        this.cardPair = cardPair;
    }

    @Override
    public void execute() {
        cardPair.solved = cardPair.isMatchingSymbol(symbol);
    }
}
