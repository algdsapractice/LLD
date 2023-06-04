package com.practice.SystemDesign.snakegame.impl;

import com.practice.SystemDesign.snakegame.rules.GameObserver;

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