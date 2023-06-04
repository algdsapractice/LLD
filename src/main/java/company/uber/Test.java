package company.uber;

import java.util.Arrays;

public class Test {
    private static int[] dx = { 0, -1, 1, 0 }; // direction offsets for x-axis
    private static int[] dy = { -1, 0, 0, 1 }; // direction offsets for y-axis
    private static int[][][] memo; // memoization array

    public static int[] findRobot(char[][] locationMap, int[] query) {
        int rows = locationMap.length;
        int cols = locationMap[0].length;
        memo = new int[rows][cols][4];

        // loop through each cell in the location map
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (locationMap[i][j] == 'O') {
                    for (int dir = 0; dir < 4; dir++) {
                        // perform DFS with memoization
                        int count = dfs(locationMap, i, j, dir);
                        memo[i][j][dir] = count;
                        if (Arrays.equals(query, new int[] { count, i, j, dir })) {
                            return new int[] { i, j };
                        }
                    }
                }
            }
        }
        return new int[] { -1, -1 };
    }

    private static int dfs(char[][] map, int x, int y, int dir) {
        // base case: out of bounds or obstacle encountered
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length || map[x][y] == 'X') {
            return 0;
        }

        // memoization check
        if (memo[x][y][dir] != 0) {
            return memo[x][y][dir];
        }

        // recursive case: move in current direction and continue search
        int count = 1;
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        count += dfs(map, nx, ny, dir);
        memo[x][y][dir] = count;
        return count;
    }

    public static void main(String[] args) {
        char[][] map = { { 'O', 'E', 'E', 'E', 'X' }, { 'E', 'O', 'X', 'X', 'X' }, { 'E', 'E', 'E', 'E', 'E' },
                { 'X', 'E', 'O', 'E', 'E' }, { 'X', 'E', 'X', 'E', 'X' } };
        int[] query = { 2, 2, 4, 1 };
        int[] result = findRobot(map, query);
        if (result[0] == -1 && result[1] == -1) {
            System.out.println("No robot found that matches the query");
        } else {
            System.out.println("Robot found at index (" + result[0] + ", " + result[1] + ")");
        }
    }
}
