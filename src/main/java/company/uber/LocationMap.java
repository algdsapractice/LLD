package company.uber;

/*

Given two inputs,

First input is the location map, a 2D array

| O | E | E | E | X |
| E | O | X | X | X |
| E | E | E | E | E |
| X | E | O | E | E |
| X | E | X | E | X |

O = Robot, E = Empty, X = blocker

Second input is the query. It’s a 1D array consisting of distance to the closest blocker in the order from left, top, bottom and right

[2, 2, 4, 1] -> This means distance of 2 to the left blocker, 2 to the top blocker, 4 to the bottom blocker and 1 to the right blocker

Note: The location map boundary is also considered blocker, meaning if the robot hits the boundary it also means it’s hitting the blocker.

Write a function that takes these two inputs and returns the index of the robots (if any) that matches the query that we’re looking for.


Solution for the example above would be the robot located at index [1, 1]

| O | E | E | E | X |
| E | O | X | X | X |
| E | E | E | E | E |
| X | E | O | E | E |
| X | E | X | E | X |
 */


import java.util.Arrays;

public class LocationMap {
    private static int[] dx = {0, -1, 1, 0}; // direction offsets for x-axis
    private static int[] dy = {-1, 0 , 0, 1}; // direction offsets for y-axis


    public static int[] findRobot(char[][] locationMap, int[] query) {


        int rows = locationMap.length;
        int cols = locationMap[0].length;

        // loop through each cell in the location map
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (locationMap[i][j] == 'O') {
                    int[] marker =  new int[query.length];
                    Arrays.fill(marker,1);
                    for (int k = 0; k < 4; k++) {

                        int x = i + dx[k];
                        int y = j + dy[k];

                        if ( x >= 0 && y >=0 && y < locationMap[0].length && x < locationMap.length && locationMap[x][y] != 'X') {
                            marker[k] += marker[k];
                        }
                    }
                    System.out.println(Arrays.toString(marker));
                    if (Arrays.equals(query, marker)) {
                        return new int[]{i, j};
                    }


                }


            }
        }

        return new int[]{-1, -1};
    }


    public static void main(String[] args) {
        char[][] map = {{'O', 'E', 'E', 'E', 'X'},
                        {'E', 'O', 'X', 'X', 'X'},
                        {'E', 'E', 'E', 'E', 'E'},
                        {'X', 'E', 'O', 'E', 'E'},
                        {'X', 'E', 'X', 'E', 'X'}};
        int[] query = {2, 2, 4, 1};

       // int [] query ={2,2,2,1};

        int[] result = findRobot(map, query);
        if (result == null) {
            System.out.println("No robot found that matches the query");
        } else {
            System.out.println("Robot found at index (" + result[0] + ", " + result[1] + ")");
        }
    }
}