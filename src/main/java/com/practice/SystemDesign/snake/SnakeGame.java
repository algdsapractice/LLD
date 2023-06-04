package com.practice.SystemDesign.snake;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
public class SnakeGame {
    private int size=0;
    private Grid grid;
    private Food food;
    private Snake snake;

    public SnakeGame(Grid grid,Food food) {
        this.grid = grid;
        this.food =food;
        this.snake = new Snake(food,grid);
    }


    public int move(String direction){
        int[] foodPos= getFoodPosition();
        log.info("food position: {}",foodPos);
        Pos nextPos= getNextMove(snake.getBody().getFirst(),direction);
        return snake.move(direction,nextPos,foodPos);
    }
    private int[] getFoodPosition() {
        return (food.getFoodIndex() < grid.getHeight()) ? food.getFoodLocation()[food.getFoodIndex()] : null;
    }

    private Pos getNextMove(Pos head, String direction) {
        int x=head.getX();
        int y=head.getY();
        Pos newPos=new Pos(x,y);
        switch (direction) {
            case "U":
                newPos.setX(--x);
                break;
            case "D":
                newPos.setX(++x);
                break;
            case "L":
                newPos.setY(--y);
                break;
            case "R":
                newPos.setY(++y);
                break;
        }
        return newPos;
    }


//    public static void main(String[] args) {
//
//        SnakeGame game = new SnakeGame();
//        game.move("R");
//        game.move("D");
//        game.move("R");
//        game.move("U");
//        game.move("L");
//        game.move("U");
//    }


}
