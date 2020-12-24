package com.spotit.gamev2;

public class RunGameCommand extends Command {

    private GameStateMachine gsm;

    public RunGameCommand(GameStateMachine gsm) {
        this.gsm = gsm;
    }

    @Override
    public void execute() {
        gsm.changeState(GameStateMachine.GameStates.RUN);
    }
}
