package com.practice.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SnakeGameTest {
    private SnakeGame snakeGame;

    @BeforeEach
    public void setUp() {
        int width = 3;
        int height = 2;
        int[][] food_location = {{1, 2}, {0, 1}};
        Food food = new Food(food_location,0);
        Grid grid = new Grid(height,width);
        snakeGame = new SnakeGame(grid,food);
    }

    @Test
    public void testMove() {

        Assertions.assertEquals(0, snakeGame.move("R")); // Return 0
        Assertions.assertEquals(0, snakeGame.move("D")); // Return 0
        Assertions.assertEquals(1, snakeGame.move("R")); // Return 1 , snake eats first food
        Assertions.assertEquals(1, snakeGame.move("U")); // Return 1
        Assertions.assertEquals(2, snakeGame.move("L")); // Return 2
        Assertions.assertEquals(-1, snakeGame.move("U")); // Return -1
    }
}