package com.spotit.gamev2;

public class EndOfGameCommand extends Command {

    private GameStateMachine gsm;

    public EndOfGameCommand(GameStateMachine gameStateMachine) {
        gsm = gameStateMachine;
    }

    @Override
    public void execute() {
        gsm.changeState(GameStateMachine.GameStates.END);
    }
}
