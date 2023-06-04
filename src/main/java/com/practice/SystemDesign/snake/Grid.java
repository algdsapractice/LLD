package com.practice.SystemDesign.snake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grid {
    public  int height;
    public  int width;


//    public Grid(){
//        loadConfig();
//    }

//    private void loadConfig() {
//        Properties properties = new Properties();
//        try (InputStream inputStream = SnakeGame.class.getClassLoader().getResourceAsStream("config.properties")) {
//            properties.load(inputStream);
//            WIDTH = Integer.parseInt(properties.getProperty("game.width"));
//            HEIGHT = Integer.parseInt(properties.getProperty("game.height"));
//            // Read the food positions
//            String[] foodPositions = properties.getProperty("game.food").split(",");
//            FOOD = new int[foodPositions.length][2];
//            for (int i = 0; i < foodPositions.length; i++) {
//                String[] position = foodPositions[i].split("-");
//                FOOD[i][0] = Integer.parseInt(position[0]);
//                FOOD[i][1] = Integer.parseInt(position[1]);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}







