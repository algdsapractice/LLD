package company.testKr;

import java.util.*;

public class SquirrelJumps {

    public static int[] maxJumps(int[] bars) {
        int n = bars.length;
        int[] dp = new int[n];
        int[] prev = new int[n]; // stores the previous bar that leads to the maximum jumps
        dp[0] = 1;
        int maxJumps = 1;
        int lastBarIndex = 0;

        for (int i = 1; i < n; i++) {
            int maxJumpsFromPrevBar = 0;
            int prevBarIndex = -1;
            for (int j = 1; j <= 2 && i - j >= 0 && bars[i-j] < bars[i]; j++) {
                if (dp[i-j] > maxJumpsFromPrevBar) {
                    maxJumpsFromPrevBar = dp[i-j];
                    prevBarIndex = i-j;
                }
            }
            dp[i] = maxJumpsFromPrevBar + 1;
            prev[i] = prevBarIndex;
            if (dp[i] > maxJumps) {
                maxJumps = dp[i];
                lastBarIndex = i;
            }
        }

        int[] path = new int[maxJumps];
        int currBarIndex = lastBarIndex;
        for (int i = maxJumps-1; i >= 0; i--) {
            path[i] = currBarIndex;
            currBarIndex = prev[currBarIndex];
        }

        return path;
    }

//
//    public static void main(String[] args) {
//        int[] bars = {6, 4, 14, 12, 10, 8, 6, 4, 6, 10, 14};
//        int maxJumps = maxJumps(bars);
//        System.out.println("Maximum number of jumps: " + maxJumps);
//    }
}