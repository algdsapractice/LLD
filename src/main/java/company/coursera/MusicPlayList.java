package company.coursera;
import java.util.Arrays;

public class MusicPlayList {
    private int N;
    private int GOAL;
    private int K;
    private final int MOD = 1000000007;
    private long[][] dp;

    /**
     * Recursive function to calculate the number of valid playlists.
     * @param countSong The current number of songs in the playlist.
     * @param countUnique The current number of unique songs in the playlist.
     * @return The number of valid playlists.
     */
    private long solve(int countSong, int countUnique) {
        // Base case: If the playlist reaches the goal length
        if (countSong == GOAL) {
            // Return 1 if all unique songs are used, else return 0
            return (countUnique == N) ? 1 : 0;
        }

        // If the result is already computed, return the cached result
        if (dp[countSong][countUnique] != -1) {
            return dp[countSong][countUnique];
        }

        long result = 0;

        // Option 1: Play a unique song (if we haven't used all unique songs)
        if (countUnique < N) {
            result += (N - countUnique) * solve(countSong + 1, countUnique + 1);
            result %= MOD;
        }

        // Option 2: Replay a song (if there are more than K unique songs available to replay)
        if (countUnique > K) {
            result += (countUnique - K) * solve(countSong + 1, countUnique);
            result %= MOD;
        }

        // Memoize the result
        dp[countSong][countUnique] = result;

        return result;
    }

    /**
     * Main function to compute the number of music playlists.
     * @param n The number of different songs.
     * @param goal The length of the playlist.
     * @param k The number of recent songs that cannot be replayed.
     * @return The number of possible playlists modulo 10^9 + 7.
     */
    public int numMusicPlaylists(int n, int goal, int k) {
        this.N = n;
        this.GOAL = goal;
        this.K = k;
        // Initialize dp array with -1 for memoization
        this.dp = new long[goal + 1][n + 1];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        // Start solving from an empty playlist (0 songs, 0 unique)
        return (int) solve(0, 0);
    }

    public static void main(String[] args) {
        MusicPlayList solution = new MusicPlayList();
        int n = 3;
        int goal = 3;
        int k = 1;
        System.out.println(solution.numMusicPlaylists(n, goal, k)); // Output example
    }
}
