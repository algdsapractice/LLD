package com.practice.SystemDesign.snakegame;

import com.practice.SystemDesign.snakegame.impl.SnakeGameObserver;
import com.practice.SystemDesign.snakegame.rules.GameObserver;
import com.practice.SystemDesign.snakegame.rules.GameState;
import com.practice.SystemDesign.snakegame.state.PlayingState;

public class SnakeGame {
    private final GameState playingState;
    private GameState currentState;

    public SnakeGame(int width, int height) {
        playingState = new PlayingState(width, height);
        currentState = playingState;
    }

    public int move(String direction) {
        return currentState.move(direction);
    }

    public void pause() {
        currentState.pause();
    }

    public void resume() {
        currentState.resume();
    }

    public void restart() {
        currentState.restart();
    }

    public void setState(GameState state) {
        currentState = state;
    }

    public static void main(String[] args) {
        int width = 10;
        int height = 10;
        SnakeGame game = new SnakeGame(width, height);

        // Register observer
        GameObserver observer = new SnakeGameObserver();
        ((PlayingState) game.playingState).registerGameOverObserver(observer);

        // Start the game
        game.move("RIGHT");
        game.move("RIGHT");
        game.move("DOWN");
        game.move("DOWN");
        game.move("LEFT");
        game.move("LEFT");
        game.move("UP");
        game.move("UP");
        game.move("UP");
        game.move("RIGHT");
    }
}