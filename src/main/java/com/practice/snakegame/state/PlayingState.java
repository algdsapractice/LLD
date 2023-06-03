package com.practice.snakegame.state;

import com.practice.snakegame.rules.FoodObserver;
import com.practice.snakegame.Snake;
import com.practice.snakegame.rules.GameOverObserver;
import com.practice.snakegame.rules.GameState;

import java.util.*;

public class PlayingState implements GameState, FoodObserver {
    private final Set<FoodObserver> foodObservers;

    private final int width;
    private final int height;
    private final List<int[]> foodList;
    private final Snake snake;
    private final List<GameOverObserver> gameOverObservers;

    public PlayingState(int width, int height) {
        this.width = width;
        this.height = height;
        this.foodList = new ArrayList<>();
        this.snake = new Snake();
        this.gameOverObservers = new ArrayList<>();
        this.foodObservers = new HashSet<>();
    }



    @Override
    public int move(String direction) {
        int[] head = snake.getHead();
        int[] newHead = getNextPosition(head, direction);

        // Check for collision with food
        boolean isFoodEaten = false;
        Iterator<int[]> iterator = foodList.iterator();
        while (iterator.hasNext()) {
            int[] food = iterator.next();
            if (Arrays.equals(newHead, food)) {
                snake.grow();
                iterator.remove();
                isFoodEaten = true;
                break;
            }
        }

        // Move the snake
        snake.move(newHead);

        // Check for game over conditions
        if (snake.isOutOfBounds(width, height) || snake.isCollisionWithBody()) {
            notifyGameOver();
            return -1;
        }

        // Generate new food if previous food was eaten
        if (isFoodEaten) {
            generateFood();
        }

        return snake.getLength();
    }

    @Override
    public void pause() {
        // Transition to the "Paused" state
        System.out.println("Game Paused");
    }

    @Override
    public void resume() {
        // No action in this state
    }

    @Override
    public void restart() {
        // Reset the game state
        snake.reset();
        foodList.clear();
        generateFood();
    }

    @Override
    public void onFoodGenerated(int x, int y) {
        foodList.add(new int[]{x, y});
    }

    public void registerGameOverObserver(GameOverObserver observer) {
        gameOverObservers.add(observer);
    }

    private void generateFood() {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);

        while (snake.isOccupyingPosition(x, y)) {
            x = random.nextInt(width);
            y = random.nextInt(height);
        }

        notifyFoodGenerated(x, y);
    }

    private void notifyFoodGenerated(int x, int y) {
        for (FoodObserver observer : foodObservers) {
            observer.onFoodGenerated(x, y);
        }
    }

    private void notifyGameOver() {
        for (GameOverObserver observer : gameOverObservers) {
            observer.onGameOver();
        }
    }

    public void registerFoodObserver(FoodObserver observer) {
        foodObservers.add(observer);
    }

    public void unregisterFoodObserver(FoodObserver observer) {
        foodObservers.remove(observer);
    }

    private int[] getNextPosition(int[] position, String direction) {
        int x = position[0];
        int y = position[1];
        switch (direction) {
            case "UP":
                x--;
                break;
            case "DOWN":
                x++;
                break;
            case "LEFT":
                y--;
                break;
            case "RIGHT":
                y++;
                break;
        }
        return new int[]{x, y};
    }
}
