package com.spotit.gamev2;

import com.badlogic.gdx.Input.Keys;

public class GameStateMachine {

    public enum GameStates {
        BEGIN, RUN, END;
    }

    private GameStates gameState;
    private CommandQueue commandQueue;
    private final Deck deck;

    public GameStateMachine(CommandQueue commandQueue, Deck deck) {
        gameState = GameStates.BEGIN;
        this.commandQueue = commandQueue;
        this.deck = deck;
    }

    public GameStates handleKeyInput(int keycode) {
        switch (gameState) {
            case BEGIN:
                if (keycode == Keys.SPACE) {
                    commandQueue.add(new RunGameCommand(this));
                }
                break;
            case RUN:

                break;
            case END:
                if (keycode == Keys.SPACE) {
                    commandQueue.add(new ResetGameCommand(this, deck));
                }
                break;
        }
        return gameState;
    }

    public void changeState(GameStates state) {
        gameState = state;
    }

    public GameStates getState() {
        return gameState;
    }
}
