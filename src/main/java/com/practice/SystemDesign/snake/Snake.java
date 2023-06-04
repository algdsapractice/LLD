package com.practice.SystemDesign.snake;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;

@Data
@Slf4j
public class Snake {


    private Deque<Pos> body;
    private int size;
    private Food food;
    private Grid grid;

    public Snake(Food food,Grid grid) {
        Pos initialPos = new Pos(0,0);
        this.body = new LinkedList<>();
        this.body.add(initialPos);
        this.size = 0;
        this.food=food;
        this.grid = grid;
    }
    public int move(String direction,Pos nextPos,int[] foodPosition) {
        log.info("Moving snake in direction: {}", direction);
        Pos head = body.getFirst();
        Pos foodPoss = null;
        if (isNextMoveValid(nextPos)){
            log.info("Valid move: {}", nextPos);
            if (null!=foodPosition ){
                log.debug("Food found at position: {}", foodPoss);
                foodPoss= new Pos(foodPosition[0],foodPosition[1]);
                if(isSnakeFoodFound(nextPos,foodPoss)){
                    eatFood(foodPoss);
                } else {
                    moveSnake();
                }
            }
            body.addFirst(nextPos);
            log.info("Snake moved to position: {}",nextPos);

        } else {
            log.info("Quit the game");
            return -1;
        }
        return size;
    }

    private static boolean isSnakeFoodFound(Pos nextPos, Pos foodPoss) {
        return foodPoss.equals(nextPos);
    }


    private  boolean isNextMoveValid(Pos nextPos) {
        boolean isValid = (nextPos.getX() >= 0 && nextPos.getX() < grid.getHeight() && nextPos.getY() >= 0 && nextPos.getY() < grid.width);
        if (!isValid) {
            log.warn("Invalid path: {}", nextPos);
        }
        return isValid;
    }

    private void moveSnake() {
        log.info("Snake did not find food");
        body.removeLast();
    }

    private void eatFood(Pos foodPoss) {
        body.addFirst(foodPoss);
        this.size++;
        log.info("Snake  after eating: food {} ,  size is : {}",food.getFoodIndex()+1, size);
        log.info("ssss {}  {}",food.getFoodLocation()[0].length ,food.getFoodIndex());
        if(food.getFoodLocation()[0].length>food.getFoodIndex()){
            food.incrementFoodIndex();
        }
        log.info("Retrieved Food {} , at position {}",food.getFoodIndex(),food.getFoodLocation()[food.getFoodIndex()-1]);


    }

}
