package com.spotit.gamev2;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SymbolSprite {

    private final Symbol symbol;
    private final Sprite sprite;

    public SymbolSprite(Symbol symbol, Sprite sprite) {
        this.symbol = symbol;
        this.sprite = sprite;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return "SymbolSprite{" +
                "symbol=" + symbol.toString() +
                ", sprite=" + sprite.toString() +
                '}';
    }
}
