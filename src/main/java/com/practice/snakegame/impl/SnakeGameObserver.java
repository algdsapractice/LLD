package com.practice.snakegame.impl;

import com.practice.snakegame.rules.GameObserver;

public class SnakeGameObserver implements GameObserver {
    @Override
    public void onFoodGenerated(int x, int y) {
        System.out.println("Food generated at: (" + x + ", " + y + ")");
    }

    @Override
    public void onGameOver() {
        System.out.println("Game Over");
    }
}