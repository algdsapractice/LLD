package com.practice.SystemDesign.snakegame.rules;

public interface GameState {
    int move(String direction);
    void pause();
    void resume();
    void restart();
}
