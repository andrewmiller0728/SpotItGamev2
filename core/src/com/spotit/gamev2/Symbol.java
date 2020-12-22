package com.spotit.gamev2;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Objects;


public class Symbol {


    /* Variables */

    private String name;
    private Sprite sprite;
    private boolean selected;


    /* Constructor */

    public Symbol(String name) {
        this.name = name.toUpperCase();
        sprite = null;
        selected = false;
    }

    public Symbol(String name, Sprite s) {
        this.name = name.toUpperCase();
        sprite = s;
        selected = false;
    }


    /* Methods */

    public String getName() {
        return name;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Symbol->{" +
                "name=\"" + name + "\"" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Symbol symbol = (Symbol) o;
        return name.equals(symbol.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
