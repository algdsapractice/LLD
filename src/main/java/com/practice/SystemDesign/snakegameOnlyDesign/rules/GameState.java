package com.practice.SystemDesign.snakegameOnlyDesign.rules;

public interface GameState {
    int move(String direction);
    void pause();
    void resume();
    void restart();
}
