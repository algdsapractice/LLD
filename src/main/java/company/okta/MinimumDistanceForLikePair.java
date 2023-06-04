package company.okta;

import java.util.HashMap;
import java.util.Map;

public class MinimumDistanceForLikePair {

    public static void main(String[] args) {
        String text = "abc def ghi abc jkl abc ghi mno def";
        String[] words = text.split(" ");
        Map<String, Integer> lastSeen = new HashMap<>();
        int minDist = Integer.MAX_VALUE;
        String closestPair = "";

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (lastSeen.containsKey(word)) {
                int dist = i - lastSeen.get(word);
                if (dist < minDist) {
                    minDist = dist;
                    closestPair = word + " " + words[lastSeen.get(word)];
                }
            }
            lastSeen.put(word, i);
        }

        System.out.println("Closest pair of like strings: " + closestPair);
        System.out.println("Distance: " + minDist);
    }
}
