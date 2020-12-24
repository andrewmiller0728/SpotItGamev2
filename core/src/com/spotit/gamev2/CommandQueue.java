package com.spotit.gamev2;

import com.badlogic.gdx.utils.Queue;

public class CommandQueue {

    private Queue<Command> commandQueue;

    public CommandQueue() {
        commandQueue = new Queue<>();
    }

    public void tick() {
        if (commandQueue.notEmpty()) {
            commandQueue.removeFirst().execute();
        }
    }

    public void add(Command command) {
        commandQueue.addLast(command);
    }

}