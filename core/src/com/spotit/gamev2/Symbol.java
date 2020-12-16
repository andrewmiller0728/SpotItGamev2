package com.spotit.gamev2;


import com.badlogic.gdx.graphics.Color;

import java.util.Objects;


public class Symbol {


    /* Variables */

    private String name;
    private Color color;
    private boolean selected;


    /* Constructor */

    public Symbol(String n, Color c) {
        name = n.toUpperCase();
        color = c;
        selected = false;
    }


    /* Methods */

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
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
                ", color=\"" + color.toString() + "\"" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Symbol symbol = (Symbol) o;
        return name.equals(symbol.name) && color.equals(symbol.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
