package com.practice.snake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private int[][] foodLocation;
    private  int foodIndex;
    public  void incrementFoodIndex(){
        this.foodIndex++;
    }
}
