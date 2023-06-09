package com.practice.SystemDesign.snakegameOnlyDesign.state;

import com.practice.SystemDesign.snakegameOnlyDesign.rules.GameState;

public class PausedState implements GameState {
    private final GameState previousState;

    public PausedState(GameState previousState) {
        this.previousState = previousState;
    }

    @Override
    public int move(String direction) {
        // No action in this state
        return 0;
    }

    @Override
    public void pause() {
        // No action in this state
    }

    @Override
    public void resume() {
        // Transition back to the previous state
        System.out.println("Game Resumed");
    }

    @Override
    public void restart() {
        // Transition to the "Playing" state
        previousState.restart();
    }
}
